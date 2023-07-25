package com.bca.music.player.view.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bca.music.player.R
import com.bca.music.player.core.base.BaseActivityVM
import com.bca.music.player.core.domain.model.SearchResultItem
import com.bca.music.player.core.ext.delay
import com.bca.music.player.core.ext.disposedBy
import com.bca.music.player.core.ext.observe
import com.bca.music.player.core.ext.showHidePassword
import com.bca.music.player.core.ext.showVisibleIcon
import com.bca.music.player.databinding.ActivityMainBinding
import com.bca.music.player.view.main.item.MusicEmptyItem
import com.bca.music.player.view.main.item.MusicLayoutItem
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.jakewharton.rxbinding3.widget.textChanges
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter
import java.util.concurrent.TimeUnit


class MainActivity : BaseActivityVM<ActivityMainBinding, MainActivityViewModel>() {

    private val fastAdapter by lazy { GenericFastItemAdapter() }
    private val router = MainRouter()
    // Keep track of the previously selected position
    private var previousSelectedPosition = RecyclerView.NO_POSITION
    override fun bindToolbar(): Toolbar? {
        return null
    }

    override fun enableBackButton(): Boolean {
        return false
    }

    override fun getUiBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onFirstLaunch(savedInstanceState: Bundle?) {
        baseViewModel?.doSearchResult()
        initRecycleview()

    }

    private fun initRecycleview() {
        viewBinding?.run {
            mainRecycleview.run {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = fastAdapter
            }
        }
    }

    override fun initUiListener() {
        viewBinding?.run {

            appbarSearch.specialSearchEdtxSearch.showVisibleIcon(
                R.drawable.ic_search,
                R.drawable.ic_close_black
            ).disposedBy(disposable)

            appbarSearch.specialSearchEdtxSearch.showHidePassword {
                if (it) {
                    appbarSearch.specialSearchEdtxSearch.setText("")
                }
            }

            mainSwipeRefresh.let {
                it.setOnRefreshListener {
                    baseViewModel?.doSearchResult(appbarSearch.specialSearchEdtxSearch.text.toString())
                    disposable.delay(200) {
                        it.isRefreshing = false
                    }
                }
            }

            appbarSearch.specialSearchEdtxSearch
                .textChanges()
                .debounce(800, TimeUnit.MILLISECONDS)
                .subscribe { querySearch ->
                    if (querySearch.isEmpty()) {
                        baseViewModel?.doSearchResult()
                    } else {
                        baseViewModel?.doSearchResult(querySearch.toString())
                    }
                }.disposedBy(disposable)

            fastAdapter.onClickListener = { _, _, item, position ->
                if (item is MusicLayoutItem) {
                    router.openBottomSheetMusicPlayer(
                        supportFragmentManager,
                        item.item.previewUrl.orEmpty(),
                        onDismissCallback = {
                            val item = fastAdapter.getAdapterItem(position)
                            item.isSelected = false
                            fastAdapter.notifyItemChanged(position)
                        })
                    fastAdapter.getItem(previousSelectedPosition)?.let {
                        it.isSelected = false
                    }
                    item.isSelected = true
                    previousSelectedPosition = position
                    fastAdapter.notifyItemChanged(position)
                }
                true
            }

        }
    }

    override fun bindViewModel(): MainActivityViewModel {
        return ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]
    }

    override fun observeViewModel(viewModel: MainActivityViewModel) {
        observe(viewModel.isLoadingLiveData, ::handleLoadingState)
        observe(viewModel.failureLiveData, ::handleFailure)
        observe(viewModel.searchResultEvent, ::handleResultSearch)
    }

    private fun handleLoadingState(status: Boolean?) {
        viewBinding?.mainSwipeRefresh?.isRefreshing = status == true
    }

    private fun handleResultSearch(searchResultItems: List<SearchResultItem>?) {
        fastAdapter.clear()
        if (searchResultItems?.isNotEmpty() == true) {
            searchResultItems.map { searchResultItem ->
                fastAdapter.add(MusicLayoutItem(searchResultItem))
            }
        } else {
            fastAdapter.add(MusicEmptyItem())
        }
    }

}
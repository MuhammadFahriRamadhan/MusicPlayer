package com.bca.music.player.core.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragmentVM<VB : ViewBinding, VM : BaseViewModel> : BaseFragment<VB>() {

    protected var viewModel: VM? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = bindViewModel()
        super.onViewCreated(view, savedInstanceState)
        observeViewModel(viewModel!!)
    }

    abstract fun observeViewModel(viewModel: VM)
    abstract fun bindViewModel(): VM

    protected fun handleLoading(showLoading: Boolean?) {
        if (showLoading == true) showProgress() else hideProgress()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel = null
    }
}

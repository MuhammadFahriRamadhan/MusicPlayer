package com.bca.music.player.view.main

import com.bca.music.player.core.base.BaseViewModel
import com.bca.music.player.core.domain.model.SearchResultItem
import com.bca.music.player.core.domain.usecase.SearchRepositoryUseCase
import com.bca.music.player.core.ext.TagInjection
import com.bca.music.player.core.ext.disposedBy
import com.bca.music.player.core.ext.getGeneralErrorServer
import com.bca.music.player.core.helper.NetworkHandler
import com.bca.music.player.core.helper.SingleLiveEvent
import io.reactivex.Scheduler
import javax.inject.Named

class MainActivityViewModel @javax.inject.Inject constructor(
    @Named(TagInjection.UI_Scheduler) uiSchedulers: Scheduler,
    @Named(TagInjection.IO_Scheduler) ioScheduler: Scheduler,
    networkHandler: NetworkHandler,
    private val searchResulUseCase: SearchRepositoryUseCase
) : BaseViewModel(uiSchedulers, ioScheduler, networkHandler) {

    val searchResultEvent = SingleLiveEvent<List<SearchResultItem>>()

    fun doSearchResult(queryString  : String? = null) {
        searchResulUseCase
            .getSearchResult(queryString)
            .compose(applySchedulers())
            .doOnSubscribe { isLoadingLiveData.postValue( true) }
            .doOnTerminate { isLoadingLiveData.postValue( false) }
            .subscribe({searchResultItems ->
                searchResultEvent.postValue(searchResultItems)
            },{
                handleFailure(it.getGeneralErrorServer())
            }).disposedBy(disposable)
    }
}

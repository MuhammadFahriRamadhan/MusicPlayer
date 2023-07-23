package com.bca.music.player.core.domain.usecase

import com.bca.music.player.core.domain.model.SearchResultItem
import com.bca.music.player.core.domain.repository.SearchRepository
import com.bca.music.player.core.helper.Constant
import io.reactivex.Single
import javax.inject.Inject

class SearchRepositoryUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    fun getSearchResult(querySearch : String?) : Single<List<SearchResultItem>> {
        return searchRepository.getSearchResult(term = if(querySearch.isNullOrEmpty()) Constant.QUERY_DEFAULT else querySearch   , entity = Constant.ENTITY_TRACK,10)
    }

}
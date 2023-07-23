package com.bca.music.player.core.data.repositoryimpl

import com.bca.music.player.core.data.network.api.SearchApi
import com.bca.music.player.core.domain.model.SearchResultItem
import com.bca.music.player.core.domain.repository.SearchRepository
import io.reactivex.Single
import retrofit2.Retrofit

class SearchRepositoryImpl(private val searchApi: SearchApi) : SearchRepository {

    override fun getSearchResult(
        term: String,
        entity: String,
        limit: Int
    ): Single<List<SearchResultItem>> {
        return searchApi.getSearchResult(term, entity, limit).map { it.data.map { it.toSearchResultItem() } }
    }

}
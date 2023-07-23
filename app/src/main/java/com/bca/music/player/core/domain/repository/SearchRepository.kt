package com.bca.music.player.core.domain.repository

import com.bca.music.player.core.domain.model.SearchResultItem
import io.reactivex.Single


interface SearchRepository {

    fun getSearchResult(
        term : String,
        entity : String,
        limit : Int
    ) : Single<List<SearchResultItem>>

}
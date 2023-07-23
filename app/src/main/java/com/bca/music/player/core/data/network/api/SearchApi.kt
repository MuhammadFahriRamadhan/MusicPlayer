package com.bca.music.player.core.data.network.api


import com.bca.music.player.core.data.response.SearchResultResponseItem
import id.altea.care.core.data.response.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("search")
    fun getSearchResult(
        @Query("term") term : String,
        @Query("entity") entity : String,
        @Query("limit") limit : Int
    ) : Single<Response<List<SearchResultResponseItem>>>


}
package com.terry.delivery.data.remote

import com.terry.delivery.entity.search.SearchItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

/*
 * Created by Taehyung Kim on 2021-10-14
 */
interface SearchApi {

    @GET("api/restaurants/query_search")
    fun searchItem(
        @HeaderMap headerMap: Map<String, String>,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Single<SearchItem>
}
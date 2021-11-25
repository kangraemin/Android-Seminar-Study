package com.clonecodingbm.data.remote.search

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchApi {
    @GET("api/restaurants/query_search")
    fun searchRestaurants(
        @Header("X-Api-Key") apiKey: String,
        @Header("authorization") access: String,
        @Query("query") query: String,
        @Query("page") page: Int?
    ): Single<SearchResponse>
}
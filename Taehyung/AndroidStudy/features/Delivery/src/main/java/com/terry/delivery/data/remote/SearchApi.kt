package com.terry.delivery.data.remote

import com.terry.delivery.BuildConfig
import com.terry.delivery.entity.search.SearchItem
import io.reactivex.Single
import retrofit2.http.*

/*
 * Created by Taehyung Kim on 2021-10-14
 */
interface SearchApi {

    @Headers("X-Api-Key: ${BuildConfig.searchApiKey}")
    @GET("api/restaurants/query_search")
    fun searchItem(
        @Header("authorization") accessToken: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Single<SearchItem>
}
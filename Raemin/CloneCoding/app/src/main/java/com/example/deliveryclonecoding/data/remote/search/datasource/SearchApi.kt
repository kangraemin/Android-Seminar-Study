package com.example.deliveryclonecoding.data.remote.search.datasource

import com.example.deliveryclonecoding.BuildConfig
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface SearchApi {
    @GET("api/restaurants/query_search/")
    @Headers(BuildConfig.api_key_header)
    fun search(@Query("query") query: String, @Header("Authorization") accessToken: String?): Single<RestaurantsSearchResult>
}
package com.clonecodingbm.network

import com.clonecodingbm.model.Restaurants
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface SearchApi {
    @GET("api/restaurants/query_search/{query}")
    fun searchRestaurants(@Path("query") query: String): Single<Restaurants>
}
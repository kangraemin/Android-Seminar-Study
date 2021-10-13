package com.clonecodingbm.repository

import com.clonecodingbm.model.Restaurants
import com.clonecodingbm.network.SearchApi
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchApi: SearchApi
) {
    fun searchRestaurants(query: String): Single<Restaurants> {
        return searchApi
            .searchRestaurants(query)
            .subscribeOn(Schedulers.io())
    }
}
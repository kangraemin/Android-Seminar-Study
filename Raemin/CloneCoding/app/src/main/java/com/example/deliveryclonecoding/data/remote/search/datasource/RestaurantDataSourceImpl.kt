package com.example.deliveryclonecoding.data.remote.search.datasource

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RestaurantDataSourceImpl @Inject constructor(
    private val searchApi: SearchApi
) : RestaurantDataSource {
    override fun search(query: String, accessToken: String?): Single<RestaurantsSearchResult> {
        return searchApi
            .search(query, accessToken)
            .subscribeOn(Schedulers.io())
    }
}
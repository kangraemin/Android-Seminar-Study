package com.example.deliveryclonecoding.data.remote.search.datasource

import io.reactivex.Single

interface RestaurantDataSource {
    fun search(query: String): Single<RestaurantsSearchResult>
}
package com.example.deliveryclonecoding.data.remote.search

import com.example.deliveryclonecoding.data.remote.search.datasource.RestaurantsSearchResult
import io.reactivex.Single

interface RestaurantRepository {
    fun search(query: String): Single<RestaurantsSearchResult>
}
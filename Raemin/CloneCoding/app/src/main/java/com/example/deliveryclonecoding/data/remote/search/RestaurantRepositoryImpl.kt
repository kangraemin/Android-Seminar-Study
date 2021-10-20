package com.example.deliveryclonecoding.data.remote.search

import com.example.deliveryclonecoding.data.remote.search.datasource.RestaurantDataSource
import com.example.deliveryclonecoding.data.remote.search.datasource.RestaurantsSearchResult
import io.reactivex.Single
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val restaurantDataSource: RestaurantDataSource
) : RestaurantRepository {
    override fun search(query: String): Single<RestaurantsSearchResult> {
        return restaurantDataSource
            .search(query)
    }
}
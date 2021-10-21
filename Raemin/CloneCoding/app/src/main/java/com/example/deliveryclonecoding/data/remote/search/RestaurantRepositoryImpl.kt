package com.example.deliveryclonecoding.data.remote.search

import androidx.room.EmptyResultSetException
import com.example.deliveryclonecoding.data.local.token.LocalTokenDataSource
import com.example.deliveryclonecoding.data.remote.base.BaseNetworkErrorHandler
import com.example.deliveryclonecoding.data.remote.search.datasource.RestaurantDataSource
import com.example.deliveryclonecoding.data.remote.search.datasource.RestaurantsSearchResult
import io.reactivex.Single
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val restaurantDataSource: RestaurantDataSource,
    private val tokenDataSource: LocalTokenDataSource,
    private val baseNetworkErrorHandler: BaseNetworkErrorHandler
) : RestaurantRepository {
    override fun search(query: String): Single<RestaurantsSearchResult> {
        return tokenDataSource
            .getToken()
            .flatMap {
                restaurantDataSource
                    .search(query, "Bearer ${it.accessToken}")
            }
            .compose(baseNetworkErrorHandler.applyNetworkErrorHandler())
            .onErrorResumeNext { throwable ->
                if (throwable is EmptyResultSetException) {
                    return@onErrorResumeNext restaurantDataSource.search(query, null)
                }
                throw throwable
            }
    }
}
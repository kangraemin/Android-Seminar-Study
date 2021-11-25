package com.clonecodingbm.data.remote.search

import io.reactivex.rxjava3.core.Single

interface SearchDataSource {
    fun searchRestaurants(
        apiKey: String,
        access: String,
        query: String,
        page: Int?
    ): Single<SearchResponse>
}
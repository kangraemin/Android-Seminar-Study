package com.clonecodingbm.data.remote.search

import com.clonecodingbm.BuildConfig
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val searchApi: SearchApi
) : SearchDataSource {
    override fun searchRestaurants(
        apiKey: String,
        access: String,
        query: String,
        page: Int?
    ): Single<SearchResponse> {
        return searchApi
            .searchRestaurants(apiKey, access, query, page)
            .subscribeOn(Schedulers.io())
    }
}
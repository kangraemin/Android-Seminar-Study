package com.terry.delivery.data.repository

import com.terry.delivery.data.SearchRepository
import com.terry.delivery.data.remote.SearchApi
import io.reactivex.Completable
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-10-21
 */
class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi,
) : SearchRepository {

    override fun searchWithKeyword(
        headers: Map<String, String>,
        query: String,
        page: Int
    ): Completable {
        TODO("Not yet implemented")
    }
}
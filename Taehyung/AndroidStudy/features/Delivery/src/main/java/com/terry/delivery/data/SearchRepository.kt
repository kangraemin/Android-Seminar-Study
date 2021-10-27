package com.terry.delivery.data

import com.terry.delivery.entity.search.SearchItem
import com.terry.delivery.data.remote.model.search.Ranking
import io.reactivex.Single

/*
 * Created by Taehyung Kim on 2021-10-21
 */
interface SearchRepository {

    fun searchItemQuery(
        query: String,
        page: Int
    ): Single<Result<SearchItem>>

    fun searchWithKeyword(
        accessToken: String,
        query: String,
        page: Int
    ): Single<Result<SearchItem>>

    fun getTop10RankedData(jsonString: String): Single<Ranking>
}


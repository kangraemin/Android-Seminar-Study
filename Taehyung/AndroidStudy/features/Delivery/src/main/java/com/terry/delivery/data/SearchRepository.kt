package com.terry.delivery.data

import com.terry.delivery.data.local.model.LocalToken
import com.terry.delivery.data.local.model.SearchHistory
import com.terry.delivery.data.remote.model.search.Ranking
import com.terry.delivery.entity.search.SearchItem
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

/*
 * Created by Taehyung Kim on 2021-10-21
 */
interface SearchRepository {

    fun searchItemQuery(
        query: String,
        page: Int
    ): Single<Result<SearchItem>>

    fun searchWithKeyword(
        token: LocalToken,
        query: String,
        page: Int
    ): Single<Result<SearchItem>>

    fun getTop10RankedData(jsonString: String): Single<Ranking>

    suspend fun saveSearchHistory(searchQuery: String)

    suspend fun deleteSearchHistoryAll()

    suspend fun deleteSearchHistory(title: String)

    fun getSearchHistory(count: Int): Flow<List<SearchHistory>>
}


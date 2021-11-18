package com.terry.delivery.data.repository

import com.google.gson.Gson
import com.terry.delivery.data.SearchRepository
import com.terry.delivery.data.local.dao.LocalTokenDao
import com.terry.delivery.data.local.dao.SearchHistoryDao
import com.terry.delivery.data.local.model.LocalToken
import com.terry.delivery.data.local.model.SearchHistory
import com.terry.delivery.data.remote.LoginApi
import com.terry.delivery.data.remote.SearchApi
import com.terry.delivery.data.remote.model.search.Ranking
import com.terry.delivery.entity.search.SearchItem
import com.terry.delivery.extensions.view.retryRefreshToken
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-10-21
 */
class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi,
    private val loginApi: LoginApi,
    private val localTokenDao: LocalTokenDao,
    private val searchHistoryDao: SearchHistoryDao
) : SearchRepository {

    override fun searchItemQuery(query: String, page: Int): Single<Result<SearchItem>> {
        return localTokenDao.getLocalToken()
            .subscribeOn(Schedulers.io())
            .flatMap { localToken ->
                searchWithKeyword(localToken, query, page)
            }
            .onErrorResumeNext { Single.just(Result.failure(it)) }
    }

    override fun searchWithKeyword(
        token: LocalToken,
        query: String,
        page: Int
    ): Single<Result<SearchItem>> {
        return searchApi
            .searchItem("Bearer ${token.accessToken}", query, page)
            .map { Result.success(it) }
            .retryRefreshToken(localTokenDao, loginApi, token.refreshToken)
            .onErrorResumeNext { Single.just(Result.failure(it)) }

    }

    override fun getTop10RankedData(jsonString: String): Single<Ranking> {
        return Single.just(Gson().fromJson(jsonString, Ranking::class.java))
            .subscribeOn(Schedulers.io())
    }

    override suspend fun saveSearchHistory(searchQuery: String) {
        searchHistoryDao.saveSearchHistory(SearchHistory(title = searchQuery))
    }

    override suspend fun deleteSearchHistoryAll() {
        searchHistoryDao.deleteSearchHistoryAll()
    }

    override suspend fun deleteSearchHistory(title: String) {
        searchHistoryDao.deleteSearchHistory(title)
    }

    override fun getSearchHistory(count: Int): Flow<List<SearchHistory>> {
        return searchHistoryDao.getSearchHistory(count)
    }
}
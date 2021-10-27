package com.terry.delivery.data.repository

import androidx.room.EmptyResultSetException
import com.google.gson.Gson
import com.terry.delivery.data.SearchRepository
import com.terry.delivery.data.local.dao.LocalTokenDao
import com.terry.delivery.data.remote.SearchApi
import com.terry.delivery.data.remote.model.search.Ranking
import com.terry.delivery.entity.search.SearchItem
import com.terry.delivery.exceptions.NotLoggedInException
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-10-21
 */
class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi,
    private val localTokenDao: LocalTokenDao
) : SearchRepository {

    override fun searchItemQuery(query: String, page: Int): Single<Result<SearchItem>> {
        return localTokenDao.getLocalToken()
            .subscribeOn(Schedulers.io())
            .flatMap {
                searchWithKeyword(it.accessToken, query, page)
            }
            .retryWhen { attempts ->
                attempts.map { error ->
                    if (error is EmptyResultSetException) throw NotLoggedInException()
                    else error
                }
            }
            .onErrorResumeNext { Single.just(Result.failure(it)) }
    }

    override fun searchWithKeyword(
        accessToken: String,
        query: String,
        page: Int
    ): Single<Result<SearchItem>> {
        return searchApi
            .searchItem("Bearer $accessToken", query, page)
            .map { Result.success(it) }
            .onErrorResumeNext { Single.just(Result.failure(it)) }

    }

    override fun getTop10RankedData(jsonString: String): Single<Ranking> {
        return Single.just(Gson().fromJson(jsonString, Ranking::class.java))
            .subscribeOn(Schedulers.io())
    }
}
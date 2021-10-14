package com.dohyun.baeminapp.data.repository.search

import com.dohyun.baeminapp.data.entity.Results
import com.dohyun.baeminapp.data.local.TokenDao
import com.dohyun.baeminapp.data.remote.ApiService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
        private val tokenDao: TokenDao,
        private val apiService: ApiService
): SearchRepository {

    override fun search(query: String, page: Int?): Single<Results> {
        return apiService.searchRestaurants("","Bearer ${tokenDao.getAccessToken().subscribeOn(Schedulers.io()).blockingGet()}", query, page)
    }
}
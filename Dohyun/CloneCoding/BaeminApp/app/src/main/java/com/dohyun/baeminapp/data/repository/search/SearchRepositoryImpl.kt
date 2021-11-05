package com.dohyun.baeminapp.data.repository.search

import com.dohyun.baeminapp.data.entity.Results
import com.dohyun.baeminapp.data.local.TokenDao
import com.dohyun.baeminapp.data.remote.ApiService
import com.dohyun.baeminapp.data.repository.token.TokenService
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
        private val tokenDao: TokenDao,
        private val apiService: ApiService,
        private val tokenService: TokenService
): SearchRepository {

    override fun search(query: String, page: Int?): Single<Results> {
        return tokenDao.getAccessToken().flatMap {
            apiService.searchRestaurants(apiKey ="", "Bearer $it", query, page)
        }.doOnError {
            println("SearchRepositoryImpl search ${it.message}")
        }
    }

    override fun checkToken(): Completable {
        return tokenService.checkAccessToken()
    }

    override fun updateToken(): Completable {
        return tokenService.updateAccessToken()
    }
}
package com.terry.delivery.data.repository

import com.terry.delivery.data.DeliveryRepository
import com.terry.delivery.data.local.dao.LocalTokenDao
import com.terry.delivery.data.local.model.LocalToken
import com.terry.delivery.data.mapper.mapToLocalToken
import com.terry.delivery.data.remote.LoginApi
import com.terry.delivery.data.remote.SearchApi
import com.terry.delivery.data.remote.model.LoginInfo
import com.terry.delivery.entity.login.RefreshToken
import com.terry.delivery.model.EmptyBodyException
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-09-23
 */
class DeliveryRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi,
    private val searchApi: SearchApi,
    private val localTokenDao: LocalTokenDao
) : DeliveryRepository {

    override fun login(id: String, password: String): Completable {
        return loginApi
            .getAccessToken(LoginInfo(id, password))
            .subscribeOn(Schedulers.io())
            .flatMapCompletable { token ->
                localTokenDao
                    .deleteAllTokens()
                    .andThen(localTokenDao.saveTokens(token.mapToLocalToken()))
                    .doOnError { it.printStackTrace() }
            }

    }

    override fun searchWithKeyword(
        headers: Map<String, String>,
        query: String,
        page: Int
    ): Completable {
        // TODO: 2021-10-17 키워드 서치 로직 추가
        return Completable.complete()
    }

    override fun checkLocalAccessToken(): Single<LocalToken> {
        var t: LocalToken? = null

        return localTokenDao
            .getLocalToken()
            .subscribeOn(Schedulers.io())
            .doOnError { it.printStackTrace() }
            .flatMap { token ->
                t = token
                loginApi.verifyAccessToken(accessToken = "Bearer ${token.accessToken}")
            }
            .flatMap { response ->
                if (response.isSuccessful) Single.just(t)
                else Single.just(null)
            }
    }

    override fun refreshAccessToken(): Single<RefreshToken> {
        return localTokenDao
            .getLocalToken()
            .subscribeOn(Schedulers.io())
            .doOnError { it.printStackTrace() }
            .flatMap { token ->
                loginApi.refreshAccessToken(token.refreshToken)
            }
            .flatMap { response ->
                if (response.isSuccessful) {
                    Single.just(
                        response.body()
                            ?: return@flatMap Single.error(EmptyBodyException("Response Body is empty !!"))
                    )
                } else {
                    Single.just(null)
                }
            }
    }
}


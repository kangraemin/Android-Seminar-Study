package com.terry.delivery.data.repository

import com.terry.delivery.data.LoginRepository
import com.terry.delivery.data.local.dao.LocalTokenDao
import com.terry.delivery.data.local.model.LocalToken
import com.terry.delivery.data.mapper.mapToLocalToken
import com.terry.delivery.data.remote.LoginApi
import com.terry.delivery.data.remote.SearchApi
import com.terry.delivery.data.remote.model.LoginInfo
import com.terry.delivery.entity.login.RefreshToken
import com.terry.delivery.model.EmptyBodyException
import com.terry.delivery.model.ResponseFailException
import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-09-23
 */
class LoginRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi,
    private val localTokenDao: LocalTokenDao
) : LoginRepository {

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

    override fun checkLocalAccessToken(): Maybe<LocalToken> {
        var t = LocalToken(-1, "", "")

        return localTokenDao
            .getLocalToken()
            .subscribeOn(Schedulers.io())
            .onErrorReturnItem(t)
            .doOnError { it.printStackTrace() }
            .flatMap { token ->
                t = token
                if (t.accessToken.isEmpty()) Single.just(t)
                else loginApi.verifyAccessToken(accessToken = "Bearer ${token.accessToken}")
            }
            .flatMapMaybe { response ->
                if (response is Response<*>) {
                    if (response.isSuccessful) return@flatMapMaybe Maybe.fromCompletable(Completable.complete())
                }

                return@flatMapMaybe Maybe.just(t)
            }
    }

    override fun refreshAccessToken(refreshToken: String?): Completable {
        var tempRefreshToken = refreshToken

        val singleSource = if (refreshToken == null) {
            getRefreshToken { localToken ->
                tempRefreshToken = localToken.refreshToken
                loginApi.refreshAccessToken(localToken.refreshToken)
            }
        } else {
            loginApi.refreshAccessToken(refreshToken)
        }

        return singleSource.flatMapCompletable { response ->
            if (response.isSuccessful) {
                val refreshTokenData = response.body()
                    ?: return@flatMapCompletable Completable.error(EmptyBodyException("Response Body is empty !!"))
                val accessToken = refreshTokenData.access
                    ?: return@flatMapCompletable Completable.error(EmptyBodyException("accessToken is empty !!"))
                val localRefreshToken = tempRefreshToken
                    ?: return@flatMapCompletable Completable.error(EmptyBodyException("refreshToken is empty !!"))

                updateRefreshedToken(accessToken, localRefreshToken) {
                    Completable.complete()
                }.doOnError { it.printStackTrace() }
            } else {
                Completable.error(ResponseFailException(response.errorBody()?.string()))
            }
        }
    }

    private fun getRefreshToken(invoke: (LocalToken) -> Single<Response<RefreshToken>>) =
        localTokenDao
            .getLocalToken()
            .subscribeOn(Schedulers.io())
            .doOnError { it.printStackTrace() }
            .flatMap { invoke(it) }

    private fun updateRefreshedToken(
        accessToken: String,
        refreshToken: String,
        invoke: () -> CompletableSource
    ) = localTokenDao
        .updateAccessToken(LocalToken(id = 0, accessToken, refreshToken))
        .andThen(invoke())

}


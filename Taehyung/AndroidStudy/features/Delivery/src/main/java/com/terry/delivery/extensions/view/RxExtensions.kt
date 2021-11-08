package com.terry.delivery.extensions.view

import com.terry.delivery.data.local.dao.LocalTokenDao
import com.terry.delivery.data.local.model.LocalToken
import com.terry.delivery.data.remote.LoginApi
import io.reactivex.Single
import retrofit2.HttpException

/*
 * Created by Taehyung Kim on 2021-11-02
 */
fun <T> Single<T>.retryRefreshToken(
    localTokenDao: LocalTokenDao,
    loginApi: LoginApi,
    refreshToken: String?,
): Single<T> = this.retryWhen { attempts ->
    attempts.flatMap { t ->
        if (t is HttpException && t.code() == 401 && refreshToken != null) {
            loginApi.refreshAccessToken(refreshToken)
                .flatMapCompletable { response ->
                    if (response.isSuccessful) {
                        localTokenDao.updateAccessToken(
                            LocalToken(
                                0,
                                response.body()?.access
                                    ?: throw NullPointerException("response access is null"),
                                refreshToken
                            )
                        )
                    } else {
                        throw Throwable(response.errorBody()?.string())
                    }
                }.toFlowable<T>()
        } else {
            throw t
        }
    }
}

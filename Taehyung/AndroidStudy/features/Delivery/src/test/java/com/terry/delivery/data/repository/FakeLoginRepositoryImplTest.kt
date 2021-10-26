package com.terry.delivery.data.repository

import com.terry.delivery.data.LoginRepository
import com.terry.delivery.data.local.model.LocalToken
import io.reactivex.Completable
import io.reactivex.Maybe

/*
 * Created by Taehyung Kim on 2021-10-13
 */
class FakeLoginRepositoryImplTest : LoginRepository {

    private var shouldReturnNetworkError = false
    private var shouldReturnAccessTokenInvalid = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    fun setShouldReturnAccessTokenInvalid(value: Boolean) {
        shouldReturnAccessTokenInvalid = value
    }

    override fun login(id: String, password: String): Completable {
        return if (shouldReturnNetworkError) {
            Completable.error(Throwable("Error"))
        } else {
            Completable.complete()
        }
    }

    override fun checkLocalAccessToken(): Maybe<LocalToken> {
        return if (shouldReturnAccessTokenInvalid) {
            Maybe.error(Throwable("Error"))
        } else {
            Maybe.just(
                LocalToken(
                    id = 0,
                    "test success accessToken",
                    "test success refreshToken"
                )
            )
        }
    }

    override fun refreshAccessToken(refreshToken: String?): Completable {
        return if (shouldReturnNetworkError) {
            Completable.error(Throwable("Error"))
        } else {
            Completable.complete()
        }
    }
}
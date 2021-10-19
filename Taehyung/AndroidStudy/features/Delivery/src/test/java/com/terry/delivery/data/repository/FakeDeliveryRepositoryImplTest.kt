package com.terry.delivery.data.repository

import com.terry.delivery.data.DeliveryRepository
import com.terry.delivery.data.local.model.LocalToken
import com.terry.delivery.entity.login.RefreshToken
import io.reactivex.Completable
import io.reactivex.Single

/*
 * Created by Taehyung Kim on 2021-10-13
 */
class FakeDeliveryRepositoryImplTest : DeliveryRepository {

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

    override fun searchWithKeyword(
        headers: Map<String, String>,
        query: String,
        page: Int
    ): Completable {
        return if (shouldReturnNetworkError) {
            Completable.error(Throwable("Error"))
        } else {
            Completable.complete()
        }
    }

    override fun checkLocalAccessToken(): Single<LocalToken> {
        return if (shouldReturnAccessTokenInvalid) {
            Single.error(Throwable("Error"))
        } else {
            Single.just(
                LocalToken(
                    id = 0,
                    "test success accessToken",
                    "test success refreshToken"
                )
            )
        }
    }

    override fun refreshAccessToken(): Single<RefreshToken> {
        return if (shouldReturnNetworkError) {
            Single.just(
                RefreshToken(
                    null,
                    "token_not_valid",
                    "Token is invalid or expired"
                )
            )
        } else {
            Single.just(
                RefreshToken(
                    "test success accessToken",
                    null,
                    null
                )
            )
        }
    }
}
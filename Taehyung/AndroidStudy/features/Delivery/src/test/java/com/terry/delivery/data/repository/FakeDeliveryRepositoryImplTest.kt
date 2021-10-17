package com.terry.delivery.data.repository

import com.terry.delivery.data.DeliveryRepository
import io.reactivex.Completable

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

    override fun checkLocalAccessToken(): Completable {
        return if (shouldReturnAccessTokenInvalid) {
            Completable.error(Throwable("Error"))
        } else {
            Completable.complete()
        }
    }
}
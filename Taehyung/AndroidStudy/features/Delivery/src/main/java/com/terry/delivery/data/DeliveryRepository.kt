package com.terry.delivery.data

import com.terry.delivery.data.local.model.LocalToken
import com.terry.delivery.entity.login.RefreshToken
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

/*
 * Created by Taehyung Kim on 2021-09-23
 */
interface DeliveryRepository {

    fun login(id: String, password: String): Completable

    fun searchWithKeyword(headers: Map<String, String>, query: String, page: Int): Completable

    fun checkLocalAccessToken(): Maybe<LocalToken>

    fun refreshAccessToken(refreshToken: String? = null): Single<RefreshToken>
}
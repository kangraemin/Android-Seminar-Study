package com.terry.delivery.data

import com.terry.delivery.entity.login.VerifyToken
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Response

/*
 * Created by Taehyung Kim on 2021-09-23
 */
interface DeliveryRepository {

    fun login(id: String, password: String): Completable

    fun searchWithKeyword(headers: Map<String, String>, query: String, page: Int): Completable

    fun checkLocalAccessToken(): Single<Response<VerifyToken>>
}
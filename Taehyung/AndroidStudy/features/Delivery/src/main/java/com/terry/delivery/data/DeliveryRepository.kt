package com.terry.delivery.data

import io.reactivex.Completable

/*
 * Created by Taehyung Kim on 2021-09-23
 */
interface DeliveryRepository {

    fun login(id: String, password: String): Completable

    fun searchWithKeyword(headers: Map<String, String>, query: String, page: Int): Completable

    fun checkLocalAccessToken(): Completable
}
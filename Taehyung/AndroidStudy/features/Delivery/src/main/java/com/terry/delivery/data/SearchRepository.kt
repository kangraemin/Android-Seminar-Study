package com.terry.delivery.data

import io.reactivex.Completable

/*
 * Created by Taehyung Kim on 2021-10-21
 */
interface SearchRepository {

    fun searchWithKeyword(headers: Map<String, String>, query: String, page: Int): Completable
}


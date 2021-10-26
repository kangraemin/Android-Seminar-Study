package com.terry.delivery.data

import com.terry.delivery.entity.search.SearchItem
import io.reactivex.Single

/*
 * Created by Taehyung Kim on 2021-10-21
 */
interface SearchRepository {

    fun searchWithKeyword(
        accessToken: String,
        query: String,
        page: Int
    ): Single<Result<SearchItem>>
}

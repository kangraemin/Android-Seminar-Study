package com.clonecodingbm.data.repository.search

import com.clonecodingbm.data.local.recentsearch.RecentSearchEntity
import com.clonecodingbm.data.remote.search.SearchResponse
import io.reactivex.rxjava3.core.Single

interface SearchRepository {
    fun getRecentSearches(): Single<List<RecentSearchEntity>>
    fun searchAndSave(query: String, page: Int): Single<SearchResponse>
}
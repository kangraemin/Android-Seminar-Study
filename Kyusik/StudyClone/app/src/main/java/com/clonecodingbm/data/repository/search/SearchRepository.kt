package com.clonecodingbm.data.repository.search

import com.clonecodingbm.data.local.recentsearch.RecentSearchEntity
import io.reactivex.rxjava3.core.Single

interface SearchRepository {
    fun getRecentSearches(): Single<List<RecentSearchEntity>>
}
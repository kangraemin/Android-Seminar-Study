package com.clonecodingbm.data.repository.search

import com.clonecodingbm.data.local.recentsearch.RecentSearchDataSource
import com.clonecodingbm.data.local.recentsearch.RecentSearchEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val recentSearchDataSource: RecentSearchDataSource
) : SearchRepository {
    override fun getRecentSearches(): Single<List<RecentSearchEntity>> {
        return recentSearchDataSource
            .getRecentSearches()
    }
}
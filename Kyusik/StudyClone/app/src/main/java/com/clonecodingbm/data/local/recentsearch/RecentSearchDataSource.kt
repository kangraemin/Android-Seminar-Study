package com.clonecodingbm.data.local.recentsearch

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface RecentSearchDataSource {
    fun getRecentSearches(): Single<List<RecentSearchEntity>>
    fun addRecentSearch(recentSearchEntity: RecentSearchEntity): Completable
    fun deleteRecentSearch(recentSearchEntity: RecentSearchEntity): Completable
    fun deleteAll(): Completable
}
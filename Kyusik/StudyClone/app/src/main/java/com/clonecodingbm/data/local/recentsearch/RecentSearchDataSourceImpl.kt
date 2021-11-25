package com.clonecodingbm.data.local.recentsearch

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RecentSearchDataSourceImpl @Inject constructor(
    private val recentSearchDao: RecentSearchDao
) : RecentSearchDataSource {
    override fun getRecentSearches(): Single<List<RecentSearchEntity>> {
        return recentSearchDao
            .getRecentSearches()
            .subscribeOn(Schedulers.io())
    }

    override fun addRecentSearch(recentSearchEntity: RecentSearchEntity): Completable {
        return recentSearchDao
            .addRecentSearch(recentSearchEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun deleteRecentSearch(recentSearchEntity: RecentSearchEntity): Completable {
        return recentSearchDao
            .deleteRecentSearch(recentSearchEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun deleteAll(): Completable {
        return recentSearchDao
            .deleteAll()
            .subscribeOn(Schedulers.io())
    }

}

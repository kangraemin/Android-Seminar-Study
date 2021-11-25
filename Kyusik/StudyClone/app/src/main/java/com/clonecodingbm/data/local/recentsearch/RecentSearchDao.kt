package com.clonecodingbm.data.local.recentsearch

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface RecentSearchDao {
    @Query(value = "SELECT * FROM RecentSearches")
    fun getRecentSearches(): Single<List<RecentSearchEntity>>

    @Insert(onConflict = REPLACE)
    fun addRecentSearch(recentSearchEntity: RecentSearchEntity): Completable

    @Delete
    fun deleteRecentSearch(recentSearchEntity: RecentSearchEntity): Completable

    @Query(value = "DELETE FROM RecentSearches")
    fun deleteAll(): Completable

}
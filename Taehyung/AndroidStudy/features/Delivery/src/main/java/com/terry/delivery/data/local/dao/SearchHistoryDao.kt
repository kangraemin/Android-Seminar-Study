package com.terry.delivery.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.terry.delivery.data.local.model.SearchHistory
import kotlinx.coroutines.flow.Flow

/*
 * Created by Taehyung Kim on 2021-11-09
 */
@Dao
interface SearchHistoryDao {

    @Query("SELECT * FROM search_history LIMIT :count")
    fun getSearchHistory(count: Int): Flow<List<SearchHistory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSearchHistory(searchHistory: SearchHistory)

    @Query("DELETE FROM search_history WHERE title = :title")
    suspend fun deleteSearchHistory(title: String)

    @Query("DELETE FROM search_history")
    suspend fun deleteSearchHistoryAll()
}
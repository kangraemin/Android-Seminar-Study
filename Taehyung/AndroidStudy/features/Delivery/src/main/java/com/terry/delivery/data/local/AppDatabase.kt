package com.terry.delivery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.terry.delivery.data.local.dao.LocalTokenDao
import com.terry.delivery.data.local.dao.SearchHistoryDao
import com.terry.delivery.data.local.model.LocalToken
import com.terry.delivery.data.local.model.SearchHistory

/*
 * Created by Taehyung Kim on 2021-09-28
 */
@Database(entities = [LocalToken::class, SearchHistory::class], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTokenDao(): LocalTokenDao

    abstract fun getSearchHistoryDao(): SearchHistoryDao
}
package com.terry.delivery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.terry.delivery.data.local.dao.LocalTokenDao
import com.terry.delivery.data.model.LocalToken

/*
 * Created by Taehyung Kim on 2021-09-28
 */
@Database(entities = [LocalToken::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTokenDao(): LocalTokenDao
}
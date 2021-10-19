package com.clonecodingbm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.clonecodingbm.data.local.token.TokenDao
import com.clonecodingbm.data.local.token.TokenEntity

@Database(entities = [TokenEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tokenDao(): TokenDao
}
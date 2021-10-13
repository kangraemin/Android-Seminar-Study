package com.clonecodingbm.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TokenEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tokenDao(): TokenDao
}
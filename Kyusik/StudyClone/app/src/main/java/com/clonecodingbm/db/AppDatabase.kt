package com.clonecodingbm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.clonecodingbm.data.login.Token

@Database(entities = [Token::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tokenDao(): TokenDao
}
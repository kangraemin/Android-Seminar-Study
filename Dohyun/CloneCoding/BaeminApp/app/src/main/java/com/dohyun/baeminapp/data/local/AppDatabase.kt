package com.dohyun.baeminapp.data.local

import androidx.room.*
import com.dohyun.baeminapp.data.entity.Token

@Database(entities = [Token::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tokenDao(): TokenDao

    companion object {
        const val DB_NAME = "database-tokens"
    }

}
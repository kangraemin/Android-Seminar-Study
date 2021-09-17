package com.dohyun.baeminapp.data.local

import android.content.Context
import androidx.room.*
import com.dohyun.baeminapp.data.entity.Token

@Database(entities = [Token::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tokenDao(): TokenDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database-tokens"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}
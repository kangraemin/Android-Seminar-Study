package com.example.deliveryclonecoding.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.deliveryclonecoding.data.local.token.LocalTokenDao
import com.example.deliveryclonecoding.data.local.token.LocalTokenItem

@Database(entities = [LocalTokenItem::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun tokenDao(): LocalTokenDao

    // ref : https://developer.android.com/codelabs/android-room-with-a-view-kotlin#7
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
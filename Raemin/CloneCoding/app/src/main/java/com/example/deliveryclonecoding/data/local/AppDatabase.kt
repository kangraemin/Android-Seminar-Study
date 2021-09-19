package com.example.deliveryclonecoding.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.deliveryclonecoding.data.local.token.LocalTokenDao
import com.example.deliveryclonecoding.data.local.token.LocalTokenItem

@Database(entities = [LocalTokenItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tokenDao(): LocalTokenDao
}
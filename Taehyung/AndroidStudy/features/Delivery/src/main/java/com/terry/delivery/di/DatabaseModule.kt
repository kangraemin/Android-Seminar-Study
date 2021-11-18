package com.terry.delivery.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.terry.delivery.data.local.AppDatabase
import com.terry.delivery.data.local.dao.LocalTokenDao
import com.terry.delivery.data.local.dao.SearchHistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
 * Created by Taehyung Kim on 2021-09-28
 */
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    private val APP_DB_MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "CREATE TABLE `search_history` (`id` INTEGER, `title` TEXT NOT NULL, PRIMARY KEY(`id`))"
            )
            database.execSQL(
                "CREATE UNIQUE INDEX IF NOT EXISTS index_search_history_title ON search_history(title)"
            )
        }
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "delivery_database"
        )
            .addMigrations(APP_DB_MIGRATION_1_2)
            .build()

    @Singleton
    @Provides
    fun provideLocalTokenDao(appDatabase: AppDatabase): LocalTokenDao =
        appDatabase.getTokenDao()

    @Singleton
    @Provides
    fun provideSearchHistoryDao(appDatabase: AppDatabase): SearchHistoryDao =
        appDatabase.getSearchHistoryDao()

}
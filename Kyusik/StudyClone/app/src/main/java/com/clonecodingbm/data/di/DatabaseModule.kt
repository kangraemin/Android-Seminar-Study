package com.clonecodingbm.data.di

import android.content.Context
import androidx.room.Room
import com.clonecodingbm.data.local.AppDatabase
import com.clonecodingbm.data.local.recentsearch.RecentSearchDao
import com.clonecodingbm.data.local.login.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "word_database"
        ).build()

    @Provides
    @Singleton
    fun provideTokenDao(appDatabase: AppDatabase): UserDao =
        appDatabase.userDao()

    @Provides
    @Singleton
    fun provideRecentSearchDao(appDatabase: AppDatabase): RecentSearchDao =
        appDatabase.recentSearchDao()
}
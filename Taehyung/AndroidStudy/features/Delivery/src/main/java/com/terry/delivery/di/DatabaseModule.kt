package com.terry.delivery.di

import android.content.Context
import androidx.room.Room
import com.terry.delivery.data.local.AppDatabase
import com.terry.delivery.data.local.dao.LocalTokenDao
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

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "delivery_database"
        ).build()

    @Singleton
    @Provides
    fun provideLocalTokenDao(appDatabase: AppDatabase): LocalTokenDao =
        appDatabase.getTokenDao()

}
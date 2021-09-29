package com.dohyun.baeminapp.di

import android.app.Application
import androidx.room.Room
import com.dohyun.baeminapp.data.local.AppDatabase
import com.dohyun.baeminapp.data.local.AppDatabase.Companion.DB_NAME
import com.dohyun.baeminapp.data.local.TokenDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideLocalToken(appDatabase: AppDatabase): TokenDao =
        appDatabase.tokenDao()
}
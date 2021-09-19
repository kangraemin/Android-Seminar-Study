package com.example.deliveryclonecoding.di

import android.content.Context
import androidx.room.Room
import com.example.deliveryclonecoding.data.local.AppDatabase
import com.example.deliveryclonecoding.data.local.token.LocalTokenDao
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
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "word_database"
        ).build()
    }

    @Provides
    fun provideTokenDao(appDatabase: AppDatabase) : LocalTokenDao {
        return appDatabase.tokenDao()
    }
}
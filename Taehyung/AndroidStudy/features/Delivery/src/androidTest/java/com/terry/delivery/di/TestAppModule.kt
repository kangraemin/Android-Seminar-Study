package com.terry.delivery.di

import android.content.Context
import androidx.room.Room
import com.terry.delivery.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

/*
 * Created by Taehyung Kim on 2021-10-11
 */
@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("test_delivery_database")
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

}
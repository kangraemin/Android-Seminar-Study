package com.dohyun.baeminapp.di

import android.content.Context
import com.dohyun.baeminapp.data.local.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefModule {

    @Provides
    @Singleton
    fun providePreference(context: Context): PreferenceManager {
        return PreferenceManager(context)
    }
}
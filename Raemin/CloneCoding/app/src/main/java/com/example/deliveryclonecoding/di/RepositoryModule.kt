package com.example.deliveryclonecoding.di

import com.example.deliveryclonecoding.data.Repository
import com.example.deliveryclonecoding.data.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindsRepository(impl: RepositoryImpl): Repository
}
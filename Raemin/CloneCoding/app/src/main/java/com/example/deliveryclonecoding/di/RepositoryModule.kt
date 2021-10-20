package com.example.deliveryclonecoding.di

import com.example.deliveryclonecoding.data.remote.login.LoginRepository
import com.example.deliveryclonecoding.data.remote.login.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindsRepository(impl: LoginRepositoryImpl): LoginRepository
}
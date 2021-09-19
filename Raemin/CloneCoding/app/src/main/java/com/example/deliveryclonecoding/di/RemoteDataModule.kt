package com.example.deliveryclonecoding.di

import com.example.deliveryclonecoding.data.remote.login.LoginDataSource
import com.example.deliveryclonecoding.data.remote.login.LoginDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteDataModule {
    @Binds
    abstract fun bindLoginDataSource(impl: LoginDataSourceImpl): LoginDataSource
}
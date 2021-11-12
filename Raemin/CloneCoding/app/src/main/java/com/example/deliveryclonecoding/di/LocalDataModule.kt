package com.example.deliveryclonecoding.di

import com.example.deliveryclonecoding.data.local.token.LocalTokenDataSource
import com.example.deliveryclonecoding.data.local.token.LocalTokenDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class LocalDataModule {
    @Binds
    abstract fun bindLocalTokenDataSource(localTokenDataSourceImpl: LocalTokenDataSourceImpl) : LocalTokenDataSource
}
package com.example.deliveryclonecoding.di

import com.example.deliveryclonecoding.data.remote.base.BaseNetworkErrorHandler
import com.example.deliveryclonecoding.data.remote.base.BaseNetworkErrorHandlerImpl
import com.example.deliveryclonecoding.data.remote.login.datasource.TokenDataSource
import com.example.deliveryclonecoding.data.remote.login.datasource.TokenDataSourceImpl
import com.example.deliveryclonecoding.data.remote.search.datasource.RestaurantDataSource
import com.example.deliveryclonecoding.data.remote.search.datasource.RestaurantDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteDataModule {
    @Binds
    abstract fun bindLoginDataSource(impl: TokenDataSourceImpl): TokenDataSource
    @Binds
    abstract fun bindRestaurantsDataSource(impl: RestaurantDataSourceImpl): RestaurantDataSource
    @Binds
    abstract fun bindBaseNetworkErrorHandler(impl: BaseNetworkErrorHandlerImpl): BaseNetworkErrorHandler
}
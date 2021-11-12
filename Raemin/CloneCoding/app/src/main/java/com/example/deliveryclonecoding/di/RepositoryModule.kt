package com.example.deliveryclonecoding.di

import com.example.deliveryclonecoding.data.remote.login.LoginRepository
import com.example.deliveryclonecoding.data.remote.login.LoginRepositoryImpl
import com.example.deliveryclonecoding.data.remote.search.RestaurantRepository
import com.example.deliveryclonecoding.data.remote.search.RestaurantRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindsRepository(impl: LoginRepositoryImpl): LoginRepository
    @Binds
    abstract fun bindsRestaurantsRepository(impl: RestaurantRepositoryImpl): RestaurantRepository
}
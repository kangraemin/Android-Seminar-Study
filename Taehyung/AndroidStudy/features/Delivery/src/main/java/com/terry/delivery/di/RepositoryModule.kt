package com.terry.delivery.di

import com.terry.delivery.data.LoginRepository
import com.terry.delivery.data.SearchRepository
import com.terry.delivery.data.repository.LoginRepositoryImpl
import com.terry.delivery.data.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/*
 * Created by Taehyung Kim on 2021-09-23
 */
@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsDeliveryRepository(
        repository: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    abstract fun bindsSearchRepository(
        repository: SearchRepositoryImpl
    ): SearchRepository
}
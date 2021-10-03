package com.terry.delivery.di

import com.terry.delivery.data.DeliveryRepository
import com.terry.delivery.data.repository.DeliveryRepositoryImpl
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
        repository: DeliveryRepositoryImpl
    ): DeliveryRepository

}
package com.dohyun.baeminapp.di

import com.dohyun.baeminapp.data.repository.LoginRepository
import com.dohyun.baeminapp.data.repository.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsLoginRepository(
        repositoryImpl: LoginRepositoryImpl
    ): LoginRepository
}
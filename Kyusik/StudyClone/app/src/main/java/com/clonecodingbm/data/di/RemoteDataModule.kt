package com.clonecodingbm.data.di

import com.clonecodingbm.data.remote.login.LoginDataSource
import com.clonecodingbm.data.remote.login.LoginDataSourceImpl
import com.clonecodingbm.data.remote.search.SearchDataSource
import com.clonecodingbm.data.remote.search.SearchDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteDataModule {
    @Binds
    abstract fun bindLoginDataSource(loginDataSourceImpl: LoginDataSourceImpl) : LoginDataSource

    @Binds
    abstract fun bindSearchDataSource(searchDataSourceImpl: SearchDataSourceImpl) : SearchDataSource
}
package com.lcw.study.clonebaemin.di


import com.lcw.study.clonebaemin.data.login.remote.LoginDataSource
import com.lcw.study.clonebaemin.data.login.remote.LoginRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteDataModule {
    @Binds
    abstract fun bindLoginDataSource(loginImpl: LoginRemoteDataSourceImpl): LoginDataSource

}
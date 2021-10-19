package com.clonecodingbm.data.di

import com.clonecodingbm.data.repository.home.HomeRepository
import com.clonecodingbm.data.repository.home.HomeRepositoryImpl
import com.clonecodingbm.data.repository.login.LoginRepository
import com.clonecodingbm.data.repository.login.LoginRepositoryImpl
import com.clonecodingbm.data.repository.mypage.MyPageRepository
import com.clonecodingbm.data.repository.mypage.MyPageRepositoryImpl
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

    @Binds
    abstract fun bindsMyPageRepository(
        repositoryImpl: MyPageRepositoryImpl
    ): MyPageRepository

    @Binds
    abstract fun bindsHomeRepository(
        repositoryImpl: HomeRepositoryImpl
    ): HomeRepository
}
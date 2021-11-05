package com.dohyun.baeminapp.di

import com.dohyun.baeminapp.data.repository.search.SearchRepository
import com.dohyun.baeminapp.data.repository.search.SearchRepositoryImpl
import com.dohyun.baeminapp.data.repository.login.LoginRepository
import com.dohyun.baeminapp.data.repository.login.LoginRepositoryImpl
import com.dohyun.baeminapp.data.repository.logout.LogoutRepository
import com.dohyun.baeminapp.data.repository.logout.LogoutRepositoryImpl
import com.dohyun.baeminapp.data.repository.mainnav.MainNavRepository
import com.dohyun.baeminapp.data.repository.mainnav.MainNavRepositoryImpl
import com.dohyun.baeminapp.data.repository.mypage.MyPageRepository
import com.dohyun.baeminapp.data.repository.mypage.MyPageRepositoryImpl
import com.dohyun.baeminapp.data.repository.token.TokenService
import com.dohyun.baeminapp.data.repository.token.TokenServiceImpl
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
    abstract fun bindsSearchRepository(
        repositoryImpl: SearchRepositoryImpl
    ): SearchRepository

    @Binds
    abstract fun bindsMyPageRepository(
        repositoryImpl: MyPageRepositoryImpl
    ): MyPageRepository

    @Binds
    abstract fun bindsLogoutRepository(
        repositoryImpl: LogoutRepositoryImpl
    ): LogoutRepository

    @Binds
    abstract fun bindsMainNavRepository(
        repositoryImpl: MainNavRepositoryImpl
    ): MainNavRepository

    @Binds
    abstract fun bindsTokenService(
            tokenServiceImpl: TokenServiceImpl
    ): TokenService

}
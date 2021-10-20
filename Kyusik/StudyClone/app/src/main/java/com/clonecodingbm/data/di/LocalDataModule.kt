package com.clonecodingbm.data.di

import com.clonecodingbm.data.local.recentsearch.RecentSearchDataSource
import com.clonecodingbm.data.local.recentsearch.RecentSearchDataSourceImpl
import com.clonecodingbm.data.local.token.TokenDataSource
import com.clonecodingbm.data.local.token.TokenDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class LocalDataModule {
    @Binds
    abstract fun bindTokenDataSource(tokenDataSourceImpl: TokenDataSourceImpl): TokenDataSource

    @Binds
    abstract fun bindRecentSearchDataSource(recentSearchDataSourceImpl: RecentSearchDataSourceImpl): RecentSearchDataSource
}
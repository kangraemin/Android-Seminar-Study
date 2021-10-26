package com.clonecodingbm.data.repository.search

import android.util.Log
import com.clonecodingbm.BuildConfig
import com.clonecodingbm.data.local.recentsearch.RecentSearchDataSource
import com.clonecodingbm.data.local.recentsearch.RecentSearchEntity
import com.clonecodingbm.data.local.token.TokenDataSource
import com.clonecodingbm.data.local.token.TokenEntity
import com.clonecodingbm.data.remote.login.LoginDataSource
import com.clonecodingbm.data.remote.login.RefreshRequest
import com.clonecodingbm.data.remote.search.SearchDataSource
import com.clonecodingbm.data.remote.search.SearchResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource,
    private val tokenDataSource: TokenDataSource,
    private val recentSearchDataSource: RecentSearchDataSource,
    private val searchDataSource: SearchDataSource
) : SearchRepository {
    override fun getRecentSearches(): Single<List<RecentSearchEntity>> {
        return recentSearchDataSource
            .getRecentSearches()
    }

    override fun searchAndSave(query: String, page: Int): Single<SearchResponse> {
        return tokenDataSource
            .getAccessToken()
            .flatMap {
                recentSearchDataSource
                    .addRecentSearch(RecentSearchEntity(query))
                    .andThen(
                        searchDataSource
                            .searchRestaurants(BuildConfig.RAMS_API_KEY, "Bearer $it", query, page)
                    )
            }
            .retryWhen { error ->
                return@retryWhen error
                    .flatMapSingle {
                        return@flatMapSingle tokenDataSource
                            .getTokens()
                            .flatMap { token ->
                                loginDataSource
                                    .refreshToken(RefreshRequest(token.refresh))
                            }
                            .flatMap { token ->
                                tokenDataSource
                                    .updateAccessToken(token.access)
                                    .andThen(Single.just(Unit))
                            }
                    }
            }
    }


    override fun deleteRecentSearch(query: String): Single<List<RecentSearchEntity>> {
        return recentSearchDataSource
            .deleteRecentSearch(RecentSearchEntity(query))
            .andThen(recentSearchDataSource.getRecentSearches())
    }

    override fun deleteRecentSearchAll(): Completable {
        return recentSearchDataSource
            .deleteAll()
    }

    private object CustomException : Throwable()
}
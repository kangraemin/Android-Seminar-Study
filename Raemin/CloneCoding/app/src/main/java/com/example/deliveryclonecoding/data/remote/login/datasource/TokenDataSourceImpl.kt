package com.example.deliveryclonecoding.data.remote.login.datasource

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TokenDataSourceImpl @Inject constructor(
    private val tokenApi: TokenApi
) : TokenDataSource {
    override fun getUserTokenFromServer(id: String, password: String): Single<TokenDataItem> {
        return tokenApi
            .getUserTokenFromServer(TokenParameter(id, password))
            .subscribeOn(Schedulers.io())
    }

    override fun refreshAccessToken(refresh: String): Single<TokenDataItem> {
        return tokenApi
            .refreshAccessToken(RefreshTokenParameter(refresh))
            .subscribeOn(Schedulers.io())
    }
}
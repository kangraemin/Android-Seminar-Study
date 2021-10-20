package com.example.deliveryclonecoding.data.remote.login.datasource

import io.reactivex.Single

interface TokenDataSource {
    fun getUserTokenFromServer(id: String, password: String): Single<TokenDataItem>
}
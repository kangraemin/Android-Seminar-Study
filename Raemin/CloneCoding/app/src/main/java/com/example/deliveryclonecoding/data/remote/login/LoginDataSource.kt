package com.example.deliveryclonecoding.data.remote.login

import io.reactivex.Single

interface LoginDataSource {
    fun login(id: String, password: String): Single<LoginDataItem>
}
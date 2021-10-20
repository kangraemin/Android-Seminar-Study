package com.example.deliveryclonecoding.data.remote.login.datasource

import com.example.deliveryclonecoding.data.remote.login.datasource.LoginDataItem
import io.reactivex.Single

interface LoginDataSource {
    fun login(id: String, password: String): Single<LoginDataItem>
}
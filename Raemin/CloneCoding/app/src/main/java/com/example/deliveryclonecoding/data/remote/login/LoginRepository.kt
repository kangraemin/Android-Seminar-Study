package com.example.deliveryclonecoding.data.remote.login

import io.reactivex.Completable

interface LoginRepository {
    fun login(id: String, password: String): Completable
    fun refreshToken(): Completable
}
package com.example.deliveryclonecoding.data

import io.reactivex.Completable

interface Repository {
    fun login(id: String, password: String): Completable
}
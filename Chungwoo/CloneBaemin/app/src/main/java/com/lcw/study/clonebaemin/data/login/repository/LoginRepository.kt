package com.lcw.study.clonebaemin.data.login.repository

import io.reactivex.Completable

interface LoginRepository {
    fun requestLogin(username: String, password: String): Completable
}
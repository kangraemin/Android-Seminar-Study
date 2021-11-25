package com.clonecodingbm.data.repository.login

import com.clonecodingbm.data.remote.login.LoginRequest
import io.reactivex.rxjava3.core.Completable

interface LoginRepository {
    fun login(loginRequest: LoginRequest): Completable
}
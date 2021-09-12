package com.lcw.study.clonebaemin.data.login

import io.reactivex.Single

interface LoginDatasource {
    fun login(id: String, password: String): Single<LoginData>

}
package com.lcw.study.clonebaemin.data.login

import io.reactivex.Single

interface LoginRepository {
    fun requestLogin(requestLoginInfoData: RequestLoginInfoData):Single<LoginData>
}
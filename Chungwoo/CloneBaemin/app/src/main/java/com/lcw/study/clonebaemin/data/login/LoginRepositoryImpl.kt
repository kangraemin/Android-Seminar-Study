package com.lcw.study.clonebaemin.data.login

import android.util.Log
import com.lcw.study.clonebaemin.data.RetrofitClient
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class LoginRepositoryImpl : LoginRepository {
    override fun requestLogin(requestLoginInfoData: RequestLoginInfoData): Single<LoginData> {
        return RetrofitClient
            .getService()
            .requestLogin(requestLoginInfoData = requestLoginInfoData)
            .subscribeOn(Schedulers.io())

    }
}
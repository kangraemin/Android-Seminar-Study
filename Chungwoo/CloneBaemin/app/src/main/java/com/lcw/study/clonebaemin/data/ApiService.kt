package com.lcw.study.clonebaemin.data

import com.lcw.study.clonebaemin.data.login.LoginData
import com.lcw.study.clonebaemin.data.login.RequestLoginInfoData
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/token")
    fun requestLogin(@Body requestLoginInfoData: RequestLoginInfoData):Single<LoginData>

}
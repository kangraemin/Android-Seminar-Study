package com.dohyun.baeminapp.network

import com.dohyun.baeminapp.entity.Token
import com.dohyun.baeminapp.entity.UserInfo
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.*

interface ApiClient {

    @POST("api/token")
    fun login(@Body user: UserInfo) : Flowable<Token>

}
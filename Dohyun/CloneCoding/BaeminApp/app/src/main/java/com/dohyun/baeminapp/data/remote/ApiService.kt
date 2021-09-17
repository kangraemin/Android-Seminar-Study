package com.dohyun.baeminapp.data.remote

import com.dohyun.baeminapp.data.entity.Token
import com.dohyun.baeminapp.data.entity.UserInfo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface ApiService {

    @POST("api/token/")
    fun login(@Body user: UserInfo) : Single<Token>

}
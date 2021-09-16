package com.clonecodingbm.network

import com.clonecodingbm.data.login.LoginDataItem
import com.clonecodingbm.data.login.LoginInfo
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/token/")
    fun login(
        @Body loginInfo: LoginInfo
    ): Single<LoginDataItem>
}
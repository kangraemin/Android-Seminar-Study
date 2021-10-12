package android.anjahyun.study.network

import android.anjahyun.study.data.LoginVO
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/token/")
    fun login(@Body body: LoginVO): Single<Response<JsonObject>>

}
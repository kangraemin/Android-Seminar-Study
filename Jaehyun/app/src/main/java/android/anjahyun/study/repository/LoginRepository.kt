package android.anjahyun.study.repository

import android.anjahyun.study.data.LoginVO
import android.anjahyun.study.network.ApiClient
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.Response

class LoginRepository {

    fun login(id: String, pw: String): Single<Response<JsonObject>> {
        return ApiClient.instance().login(LoginVO(id, pw))
    }
}
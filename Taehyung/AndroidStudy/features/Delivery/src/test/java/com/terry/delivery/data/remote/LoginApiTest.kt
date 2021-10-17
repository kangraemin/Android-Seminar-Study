package com.terry.delivery.data.remote

import com.google.common.truth.Truth.assertThat
import com.terry.delivery.ImmediateSchedulerRuleTest
import com.terry.delivery.data.remote.model.LoginInfo
import com.terry.delivery.util.Const
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/*
 * Created by Taehyung Kim on 2021-10-13
 */
class LoginApiTest {

    @get:Rule
    var schedulerRule = ImmediateSchedulerRuleTest()

    lateinit var loginApi: LoginApi

    @Before
    fun setup() {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        loginApi = Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(LoginApi::class.java)
    }

    @After
    fun teardown() {
    }

    @Test
    fun `get access token with valid input, returns success`() {
        val loginInfo = LoginInfo("delivery", "dev_baemin")

        val token = loginApi.getAccessToken(loginInfo).blockingGet()

        assertThat(token.access).isNotEmpty()
        assertThat(token.refresh).isNotEmpty()
    }

    @Test
    fun `get access token with invalid userName, returns error`() {
        val loginInfo = LoginInfo("invalidName", "dev_baemin")

        loginApi.getAccessToken(loginInfo)
            .subscribe({ token ->
                assertThat(token.detail).isNotEmpty()
            }, {
                it.printStackTrace()
            })
    }
}
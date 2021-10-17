package com.terry.delivery.data.remote

import com.google.common.truth.Truth
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
 * Created by Taehyung Kim on 2021-10-17
 */
class SearchApiTest {

    @get:Rule
    var schedulerRule = ImmediateSchedulerRuleTest()

    lateinit var searchApi: SearchApi
    lateinit var loginApi: LoginApi

    @Before
    fun setup() {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        searchApi = retrofit.create(SearchApi::class.java)

        loginApi = retrofit.create(LoginApi::class.java)
    }

    @After
    fun teardown() {
    }

    @Test
    fun `get access token and search keyword with valid headers and keyword(query), returns success`() {
        val loginInfo = LoginInfo("delivery", "dev_baemin")
        val token = loginApi.getAccessToken(loginInfo).blockingGet()

        val headers = Const.getSearchApiHeaders(token = token.access ?: "")

        val result = searchApi.searchItem(
            headerMap = headers,
            query = "떡볶이",
            page = 1
        ).blockingGet()

        Truth.assertThat(result.restaurantDto).isNotNull()
    }

    @Test
    fun `search keyword with invalid headers and keyword(query), returns error`() {
        val headers = Const.getSearchApiHeaders(token = "invalid_token_test")

        searchApi.searchItem(
            headerMap = headers,
            query = "떡볶이",
            page = 1
        ).subscribe({ searchItem ->
            Truth.assertThat(searchItem.detail).isNotEmpty()
        }, {
            it.printStackTrace()
        })
    }

    @Test
    fun `search keyword with valid keyword(query) that has empty array, returns success`() {
        val loginInfo = LoginInfo("delivery", "dev_baemin")
        val token = loginApi.getAccessToken(loginInfo).blockingGet()

        val headers = Const.getSearchApiHeaders(token = token.access ?: "")

        val result = searchApi.searchItem(
            headerMap = headers,
            query = "invalid_keyword_test",
            page = 1
        ).blockingGet()

        Truth.assertThat(result.restaurantDto).isEmpty()
    }
}
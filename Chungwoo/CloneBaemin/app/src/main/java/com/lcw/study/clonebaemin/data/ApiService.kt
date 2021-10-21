package com.lcw.study.clonebaemin.data

import com.lcw.study.clonebaemin.data.login.LoginData
import com.lcw.study.clonebaemin.data.login.RequestLoginInfoData
import com.lcw.study.clonebaemin.data.search.RequestSearchData
import com.lcw.study.clonebaemin.data.search.SearchData
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {
    @POST("api/token/")
    fun requestLogin(@Body requestLoginInfoData: RequestLoginInfoData): Single<LoginData>


    @GET("api/restaurants/query_search/")
    fun getSearch(
        @Header("X-Api-Key")apiKey:String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Single<SearchData>

}
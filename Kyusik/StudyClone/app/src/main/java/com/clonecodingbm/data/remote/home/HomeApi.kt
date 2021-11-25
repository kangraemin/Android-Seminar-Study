package com.clonecodingbm.data.remote.home

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApi {
    @GET("movie/popular")
    fun getPopular(): Single<Movies>

    @GET("trending/all/day")
    fun getTrendingMovie(): Single<Movies>

    @GET("movie/{movieId}")
    fun getMovie(@Path("movieId") movieId: Long): Single<Movie>
}
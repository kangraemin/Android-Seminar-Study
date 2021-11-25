package com.clonecodingbm.data.remote.home

import io.reactivex.rxjava3.core.Single
import retrofit2.http.Path

interface HomeDataSource {
    fun getPopular(): Single<Movies>
    fun getTrendingMovie(): Single<Movies>
    fun getMovie(@Path("movieId") movieId: Long): Single<Movie>
}
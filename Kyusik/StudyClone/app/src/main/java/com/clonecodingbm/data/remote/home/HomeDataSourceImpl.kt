package com.clonecodingbm.data.remote.home

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val homeApi: HomeApi
) : HomeDataSource {
    override fun getPopular(): Single<Movies> {
        return homeApi
            .getPopular()
            .subscribeOn(Schedulers.io())
    }

    override fun getTrendingMovie(): Single<Movies> {
        return homeApi
            .getTrendingMovie()
            .subscribeOn(Schedulers.io())
    }

    override fun getMovie(movieId: Long): Single<Movie> {
        return homeApi
            .getMovie(movieId)
            .subscribeOn(Schedulers.io())
    }
}
package com.clonecodingbm.data.repository.home

import com.clonecodingbm.data.remote.home.Movie
import com.clonecodingbm.data.remote.home.HomeDataSource
import com.clonecodingbm.data.remote.home.Movies
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepository {
    override fun getTrendingMovie(): Single<Movies> {
        return homeDataSource
            .getTrendingMovie()
    }

    override fun getMovieDetails(movieId: Long): Single<Movie> {
        return homeDataSource
            .getMovie(movieId)
    }
}
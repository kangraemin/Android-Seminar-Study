package com.clonecodingbm.data.repository.home

import com.clonecodingbm.data.remote.home.Movie
import com.clonecodingbm.data.remote.home.Movies
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface HomeRepository {
    fun getTrendingMovie(): Single<Movies>
    fun getMovieDetails(movieId: Long): Single<Movie>
}
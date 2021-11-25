package com.clonecodingbm.data.remote.home

import com.google.gson.annotations.SerializedName

data class Movies(
    val page: Long,
    @SerializedName("total_pages")
    val totalPages: Long,
    @SerializedName("total_results")
    val totalResults: Long,
    val results: List<Movie>
)

data class Movie(
    val id: Long?,
    val video: Boolean?,
    @SerializedName("vote_count")
    val voteCount: Long?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    val title: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName( "backdrop_path")
    val backdropPath: String?,
    val adult: Boolean?,
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    val popularity: Double?,
    @SerializedName("media_type")
    val mediaType: String?,
    val genres: List<Genre>?,
    val runtime: Long?
)

data class Genre(
    val id: Long,
    val name: String
)
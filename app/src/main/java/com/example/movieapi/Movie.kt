package com.example.movieapi

data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val id: Int,
    val title: String,
    val originallanguage: String,
    val originaltitle: String,
    val overview: String,
    val poster_path: String,
    val mediatype: String,
    val genreids: List<Int>,
    val popularity: Double,
    val release_date: String,
    val video: Boolean,
    val voteaverage: Double,
    val votecount: Int
)

data class MovieResponse(
    val results: List<Movie>
)

package com.example.retrofitkp.model.movie

data class MovieItem (
    val countries: List<Country>,
    val genres: List<Genre>,
    val imdbId: String,
    val kinopoiskId: Long,
    val nameEn: String,
    val nameOriginal: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val ratingImdb: Double,
    val ratingKinopoisk: Double,
    val type: String,
    val year: Int
        )
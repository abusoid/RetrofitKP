package com.example.retrofitkp.model.movie

data class ItemX(
    val countries: List<CountryX>,
    val genres: List<GenreX>,
    val imdbId: String,
    val kinopoiskId: Int,
    val nameEn: Any,
    val nameOriginal: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val ratingImdb: Double,
    val ratingKinopoisk: Double,
    val type: String,
    val year: Int
)
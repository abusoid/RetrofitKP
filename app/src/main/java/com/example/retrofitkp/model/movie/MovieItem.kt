package com.example.retrofitkp.model.movie

data class MovieItem (
    val countries: List<Country>?,
    val filmId: Int,
    val filmLength: String?,
    val genres: List<Genre>?,
    val nameEn: String?,
    val nameRu: String,
    val posterUrl: String?,
    val posterUrlPreview: String,
    val rating: String?,
    val ratingChange: Any?,
    val ratingVoteCount: Int?,
    val year: String?,
    val description: String?
        )
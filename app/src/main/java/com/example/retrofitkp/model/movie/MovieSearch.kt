package com.example.retrofitkp.model.movie

data class MovieSearch(
    val items: List<ItemX>,
    val total: Int,
    val totalPages: Int
)
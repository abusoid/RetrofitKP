package com.example.retrofitkp.data.repository

import com.example.retrofitkp.data.db.data.MovieEntity
import com.example.retrofitkp.model.movie.Movie
import retrofit2.Response

interface Repository {
    suspend fun getAllFilms(): Response<Movie>
    suspend fun getMovies(page:Int): Response<Movie>
    suspend fun getMoviesByKeyword(page: Int, keyword:String): Response<Movie>
    suspend fun getMoviesFromBD(page:Int): Movie
    fun saveListFilm(films: List<MovieEntity>, page: Int)
}
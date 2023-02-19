package com.example.retrofitkp.data.repository

import com.example.retrofitkp.model.movie.Movie
import com.example.retrofitkp.data.api.Retrofit
import com.example.retrofitkp.model.movie.MovieItem
import retrofit2.Response

class RepositoryImpl:Repository {


   /* suspend fun getMoviesByFilter(): Response<Movie>{
        return Retrofit.api.getMoviesByFilter()
    }*/
    override suspend fun getAllFilms(): Response<Movie> {
        return Retrofit.api.getMovies(1)
    }
    override suspend fun getMovies(page:Int): Response<Movie>{
        return Retrofit.api.getMovies(page)
    }

    override suspend fun getMoviesByKeyword(page: Int, keyword:String): Response<Movie> {
        return Retrofit.api.getMoviesByKeyword(page, keyword)
    }

}

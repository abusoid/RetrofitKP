package com.example.retrofitkp.data.repository

import com.example.retrofitkp.model.movie.Movie
import com.example.retrofitkp.data.api.Retrofit
import retrofit2.Response

class Repository {


    suspend fun getMoviesByFilter(): Response<Movie>{
        return Retrofit.api.getMoviesByFilter()
    }

}

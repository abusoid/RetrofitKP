package com.example.retrofitkp

import retrofit2.Response

class Repository {
    suspend fun getMoviesByName(name:String): Response<List<Movie>>{
        return Retrofit.api.getMoviesByName(name)
    }

}

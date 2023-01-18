package com.example.retrofitkp

import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

        @GET("movie")
        suspend fun getMoviesByName(name:String) : Response<List<Movie>>

        @GET("error")
        suspend fun getError():Response<Movie>


}
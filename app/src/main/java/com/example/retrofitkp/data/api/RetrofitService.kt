package com.example.retrofitkp.data.api

import com.example.retrofitkp.model.movie.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface RetrofitService {

        @Headers(
                value = ["X-API-KEY: d07a3bc9-4cd1-42b8-893c-062ca1194ab1",
                        "Content-Type: application/json"]
        )
        @GET("films?order=RATING&type=ALL&ratingFrom=8&ratingTo=10&yearFrom=1000&yearTo=3000&page=1")
        suspend fun getMoviesByFilter() : Response<Movie>

        @GET("error")
        suspend fun getError():Response<Movie>


}
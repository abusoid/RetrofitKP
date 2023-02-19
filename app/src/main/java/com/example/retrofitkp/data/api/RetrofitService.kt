package com.example.retrofitkp.data.api

import com.example.retrofitkp.model.movie.Movie
import com.example.retrofitkp.model.movie.MovieItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {

        @Headers(
                value = ["X-API-KEY: d07a3bc9-4cd1-42b8-893c-062ca1194ab1",
                        "Content-Type: application/json"]
        )
        @GET("top")
        suspend fun getMovies(
                @Query("page") page: Int,
                @Query("type") type: String = "TOP_100_POPULAR_FILMS"
        ): Response<Movie>
        @Headers(
                value = ["X-API-KEY: d07a3bc9-4cd1-42b8-893c-062ca1194ab1",
                        "Content-Type: application/json"]
        )
        @GET("search-by-keyword")
        suspend fun getMoviesByKeyword(
                @Query("page") page: Int,
                @Query("keyword") keyword: String
        ): Response<Movie>

        @GET("error")
        suspend fun getError():Response<Movie>


}
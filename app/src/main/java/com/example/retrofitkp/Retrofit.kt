package com.example.retrofitkp

import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private val retrofit by lazy {
        retrofit2.Retrofit
            .Builder()
            .baseUrl("https://api.kinopoisk.dev/movie")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api:RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }
}

package com.example.retrofitkp.data.api

import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private val retrofit by lazy {
        retrofit2.Retrofit
            .Builder()
            .baseUrl("https://kinopoiskapiunofficial.tech/api/v2.2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }
}

package com.example.retrofitkp.data.api

import com.example.retrofitkp.App.Companion.KINO_BASE_URL
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private val retrofit by lazy {
        retrofit2.Retrofit
            .Builder()
            .baseUrl(KINO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }
}

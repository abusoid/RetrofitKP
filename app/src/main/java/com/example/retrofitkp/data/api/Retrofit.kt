package com.example.retrofitkp.data.api

import com.example.retrofitkp.App.Companion.KINO_BASE_URL
import com.example.retrofitkp.App.Companion.KINO_TOKEN
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object Retrofit {
    //Логирование -->
    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(interceptor) // same for .addInterceptor(...)
        .connectTimeout(30, TimeUnit.SECONDS) //Backend is really slow
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
    //Логирование <--

    private val retrofit by lazy {
        retrofit2.Retrofit
            .Builder()
            .baseUrl(KINO_BASE_URL)
            .client(okHttpClient)//Логирование
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }
}

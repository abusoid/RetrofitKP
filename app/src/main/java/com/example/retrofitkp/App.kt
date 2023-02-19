package com.example.retrofitkp;
import android.app.Application
import android.content.Context
class App: Application() {
    companion object {
        lateinit var ctx: Context
        val KINO_TOKEN = "d07a3bc9-4cd1-42b8-893c-062ca1194ab1"
        var KINO_BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.1/films/"
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
    }
}

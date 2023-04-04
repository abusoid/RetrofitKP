package com.example.retrofitkp;
import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.retrofitkp.data.db.AppDatabase

class App: Application() {
    companion object {
        lateinit var ctx: Context
        lateinit var db: AppDatabase
        val KINO_TOKEN = "d07a3bc9-4cd1-42b8-893c-062ca1194ab1"
        var KINO_BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/"
        var dbFlg = false

    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        println("Start App")
            /*db = Room.databaseBuilder(
            ctx,
            AppDatabase::class.java, "kinopoisk"
        ).build()*/
            //db = AppDatabase.getDataBase(ctx)
    }
}

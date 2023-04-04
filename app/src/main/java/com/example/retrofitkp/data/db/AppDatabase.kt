package com.example.retrofitkp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrofitkp.data.db.data.MovieEntity
import com.example.retrofitkp.data.db.data.UserEntity

@Database(
    entities = [
        UserEntity::class,
        MovieEntity::class,
    ], version = 5
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object{
        fun getDataBase(context: Context):AppDatabase{
            return Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"kinopoisk.db").fallbackToDestructiveMigration().build()
        }
    }
}
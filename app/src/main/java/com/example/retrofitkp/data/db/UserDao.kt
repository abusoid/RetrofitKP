package com.example.retrofitkp.data.db

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.retrofitkp.data.db.data.MovieEntity
import com.example.retrofitkp.data.db.data.UserEntity
import com.example.retrofitkp.model.movie.MovieItem

@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity")
    suspend fun getAllUsers():List<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE activeFlag = 1 ORDER BY uid Desc LIMIT 1")
    suspend fun getActiveUser():UserEntity

    @Query("SELECT * FROM UserEntity ORDER BY uid Desc LIMIT 1")
    suspend fun getLastUid(): UserEntity

    @Query("SELECT * FROM MovieEntity WHERE user_id = :uid")
    suspend fun getUserMovies(uid: Int): List<MovieEntity>

    @Query("SELECT * FROM UserEntity WHERE email = :email AND " +
            "password LIKE :password ORDER BY uid Desc LIMIT 1")
    suspend fun authorizationUser(email: String, password: String): UserEntity

    @Insert
    suspend fun insertAll(vararg userEntities: UserEntity)

    @Insert
    suspend fun insertMovie(vararg movieEntities: MovieEntity)


    @Delete
    fun delete(userEntity: UserEntity)
}
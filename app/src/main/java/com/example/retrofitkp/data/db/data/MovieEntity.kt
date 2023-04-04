package com.example.retrofitkp.data.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.retrofitkp.model.movie.Country
import com.example.retrofitkp.model.movie.Genre

@Entity
data class MovieEntity(
    @PrimaryKey val filmId: Int,
    @ColumnInfo(name = "user_id") var user_id: Int,
    @ColumnInfo(name = "nameRu") val nameRu: String,
    @ColumnInfo(name = "posterUrlPreview") val posterUrlPreview: String,
    @ColumnInfo(name = "rating") val rating: String?,
    @ColumnInfo(name = "year") val year: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "page") var page: Int)


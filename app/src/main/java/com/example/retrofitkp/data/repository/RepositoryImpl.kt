package com.example.retrofitkp.data.repository

import com.example.retrofitkp.App
import com.example.retrofitkp.App.Companion.ctx
import com.example.retrofitkp.model.movie.Movie
import com.example.retrofitkp.data.api.Retrofit
import com.example.retrofitkp.data.db.AppDatabase
import com.example.retrofitkp.data.db.data.MovieEntity
import com.example.retrofitkp.model.movie.MovieItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class RepositoryImpl:Repository {
    private lateinit var db: AppDatabase

   /* suspend fun getMoviesByFilter(): Response<Movie>{
        return Retrofit.api.getMoviesByFilter()
    }*/
    override suspend fun getAllFilms(): Response<Movie> {
        return Retrofit.api.getMovies(1)
    }
    override suspend fun getMovies(page:Int): Response<Movie>{
        return Retrofit.api.getMovies(page)
    }
    override suspend fun getMoviesFromBD(page: Int): Movie {
        var movieItem: MovieItem
        var moviesResponse: List<MovieItem> = emptyList()
        val movies: List<MovieEntity> =
            AppDatabase.getDataBase(App.ctx).userDao().getUserMovies(page)
        var i = 0
        for (movie in movies) {
            movieItem = MovieItem(
                nameRu = movie.nameRu,
                posterUrlPreview = movie.posterUrlPreview,
                description = movie.description,
                filmId = movie.filmId,
                countries = null,
                filmLength = null,
                genres = null,
                nameEn = null,
                posterUrl = null,
                rating = null,
                ratingChange = null,
                ratingVoteCount = null,
                year = null
            )
            println(movieItem.nameRu)

            moviesResponse.toMutableList().add(movieItem)

        }
        return Movie(moviesResponse, page)
    }

    override fun saveListFilm(films: List<MovieEntity>, page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            db = AppDatabase.getDataBase(ctx)
            films.forEach {
                var movie  = it
                movie.page = page
                db.userDao().insertMovie(movie)
            }
        }
    }

    override suspend fun getMoviesByKeyword(page: Int, keyword:String): Response<Movie> {
        return Retrofit.api.getMoviesByKeyword(page, keyword)
    }

}

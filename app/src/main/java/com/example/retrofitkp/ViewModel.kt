package com.example.retrofitkp

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.retrofitkp.data.repository.RepositoryImpl
import com.example.retrofitkp.model.movie.Movie
import com.example.retrofitkp.model.movie.MovieItem
import kotlinx.coroutines.*
import retrofit2.Response

class ViewModel():androidx.lifecycle.ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val movieList : MutableLiveData<Response<Movie>> = MutableLiveData()
    var job: Job? = null
    var repositoryImpl= RepositoryImpl()
    val loading = MutableLiveData<Boolean>()
    val movie: MutableLiveData<MovieItem> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
    fun goToNextFragment(fragment: Fragment, nextFragment: Fragment) {
        fragment.parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.nav_host, nextFragment)
            .commit()
    }

    fun setFilms(currentFilm: MovieItem) {
        movie.value = currentFilm
    }
    fun getMovies(pageCount:Int) {
        println("start getMovies")
       //App.KINO_BASE_URL="https://kinopoiskapiunofficial.tech/api/v2.2/films/"
        viewModelScope.launch {
            movieList.value = repositoryImpl.getMovies(pageCount)
        }
    }
    fun getMoviesByKeyword(pageCount:Int, keyword:String) {
        println("start getMoviesByKeyword")
        //App.KINO_BASE_URL="https://kinopoiskapiunofficial.tech/api/v2.1/films/"

        viewModelScope.launch {
            movieList.value = repositoryImpl.getMoviesByKeyword(pageCount, keyword)
        }
    }

}
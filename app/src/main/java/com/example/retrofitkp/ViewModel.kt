package com.example.retrofitkp

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.retrofitkp.data.repository.Repository
import com.example.retrofitkp.model.movie.Movie
import com.example.retrofitkp.model.movie.MovieItem
import kotlinx.coroutines.*
import retrofit2.Response

class ViewModel():androidx.lifecycle.ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val movieList : MutableLiveData<Response<Movie>> = MutableLiveData()
    var job: Job? = null
    var repository= Repository()
    val loading = MutableLiveData<Boolean>()
    val movie: MutableLiveData<MovieItem> = MutableLiveData()

    fun getMoviesByName() {
        job = CoroutineScope(Dispatchers.IO).launch {
            //val response = Repository.getMoviesByName("Tenet")
            withContext(Dispatchers.Main) {
               /* if (response.isSuccessful) {
                    movieList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }*/
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

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
    fun goToNextFragmentWithLoad(fragment: Fragment, nextFragment: Fragment, loadSec: Long = 0) {
        CoroutineScope(Dispatchers.Default).launch {
            delay(loadSec * 1000)
            fragment.parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.nav_host, nextFragment)
                .commit()
        }
    }
    fun setFilms(currentFilm: MovieItem) {
        movie.value = currentFilm
    }
    fun getFilms(page:Int=1) {
        println("start getFilms")
        viewModelScope.launch {
            movieList.value = repository.getMoviesByFilter()
        }
    }

}
package com.example.retrofitkp

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class ViewModel():androidx.lifecycle.ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val movieList = MutableLiveData<List<Movie>>()
    var job: Job? = null

    val loading = MutableLiveData<Boolean>()

    fun getMoviesByName() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = Repository.getMoviesByName("Tenet")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    movieList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
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
}
package com.example.retrofitkp

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.retrofitkp.App.Companion.ctx
import com.example.retrofitkp.App.Companion.dbFlg
import com.example.retrofitkp.data.db.AppDatabase
import com.example.retrofitkp.data.db.AppDatabase.Companion.getDataBase
import com.example.retrofitkp.data.db.data.MovieEntity
import com.example.retrofitkp.data.repository.RepositoryImpl
import com.example.retrofitkp.model.movie.Movie
import com.example.retrofitkp.model.movie.MovieItem
import com.example.retrofitkp.data.db.data.UserEntity
import kotlinx.coroutines.*
import retrofit2.Response

class ViewModel():androidx.lifecycle.ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val movieList : MutableLiveData<Response<Movie>> = MutableLiveData()
    val movieDB : MutableLiveData<Movie> = MutableLiveData()
    var job: Job? = null
    private lateinit var db: AppDatabase
    var repositoryImpl= RepositoryImpl()
    val loading = MutableLiveData<Boolean>()
    val movie: MutableLiveData<MovieItem> = MutableLiveData()
    private lateinit var authorizationUser: UserEntity
    private lateinit var userEntity: UserEntity
    private val scope = CoroutineScope(Dispatchers.IO)

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
        println("ViewModel SetFilms")
        var userId:Int = 0
        try {
            scope.launch { userId = getDataBase(ctx).userDao().getActiveUser().uid }
        } catch (e:Exception) {
            println("Ошибка при взятии айди активного пользователя: $e")
        }
        var movieEntity = MovieEntity(
            user_id = userId,
            filmId = currentFilm.filmId,
            nameRu = currentFilm.nameRu,
            posterUrlPreview = currentFilm.posterUrlPreview,
            rating = currentFilm.rating,
            year = currentFilm.year,
            description = currentFilm.description,
            page = 1)
        scope.launch {
            insertMovie(movieEntity)
        }
        movie.value = currentFilm
    }
    suspend fun authorizationUser(email: String, password: String):UserEntity {
        println("before getDataBase")
        db = getDataBase(ctx)
        println("before userDao")
        val userDao = db.userDao()
        println("before AuthorizationUser")
        return userDao.authorizationUser(email, password)


    }
    suspend fun insertMovie(movieEntity:MovieEntity) {
        db = getDataBase(ctx)
        val userDao = db.userDao()
        userDao.insertMovie(movieEntity)
    }
    suspend fun getAllUsers():List<UserEntity> {
        db = getDataBase(ctx)
        val userDao = db.userDao()
        return userDao.getAllUsers()
    }
    suspend fun createUser(firstName: String,lastName: String, email: String, password: String, activeFlag: Int) {
        println("Start createUser")


        println("before getDataBase")
        db = getDataBase(ctx)
        println("before userDao")
        val userDao = db.userDao()
        var uid:Int = 0
        try {
            uid = userDao.getLastUid().uid + 1
        } catch (e:Exception) {
            println("Ошибка взятия последнего uid: $e")
        }
        println("uid=$uid")
        userEntity = UserEntity(uid = uid, firstName = firstName, lastName = lastName, email = email, password = password, activeFlag = activeFlag)
        println("before userEntity")
        userDao.insertAll(userEntity)
    }
    fun getMovies(pageCount:Int) {
        println("start getMovies")
        viewModelScope.launch {
            try {
                movieList.value = repositoryImpl.getMovies(pageCount)
            } catch(e:Exception) {
                println("Берем фильмы из бд")
                dbFlg = true
                movieDB.value = repositoryImpl.getMoviesFromBD(1)
            }

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
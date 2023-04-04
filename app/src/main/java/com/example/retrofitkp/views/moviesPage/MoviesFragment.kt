package com.example.retrofitkp.views.moviesPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retrofitkp.App.Companion.dbFlg
import com.example.retrofitkp.ViewModel
import com.example.retrofitkp.databinding.FragmentMoviesBinding
import com.example.retrofitkp.model.movie.MovieItem
import com.example.retrofitkp.views.detailMoviePage.DetailMovieFragment
import com.example.retrofitkp.views.loginPage.LoginFragment

class MoviesFragment : Fragment() {

    private val ViewModel by activityViewModels<ViewModel>()
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var adapter: MoviesAdapter
    private var pageCount: Int = 1
    private var methodName: String = "getMovies"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("onCreateView MoviesFragment")
        binding = FragmentMoviesBinding.inflate(inflater)
        binding.page.text = pageCount.toString()
        adapter = MoviesAdapter { item -> doClick(item) }
        with(binding) {
            moviesRV.layoutManager = GridLayoutManager(requireContext(), 2)
            moviesRV.adapter = adapter
        }

        return binding.root
    }


    companion object {

        fun newInstance() =
            MoviesFragment().apply {
            }
    }

    private fun doClick(movie: MovieItem) {
        println("doClick MoviesFragment")
        ViewModel.setFilms(movie)
        ViewModel.goToNextFragment(this, DetailMovieFragment.newInstance())

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("OnViewCreated MoviesFragment")
        setFilms(pageCount)
        binding.page.text = pageCount.toString()
        binding.page.addTextChangedListener {
            binding.previousPage.isVisible = pageCount != 1
            binding.nextPage.isVisible = pageCount <= 20
        }

        //Кнопка "Следующая"
        binding.nextPage.isVisible = pageCount <= 20
        binding.nextPage.setOnClickListener {
            println("Итерация страницы")
            pageCount++
            binding.page.text = pageCount.toString()
            println("перед вызовом SetList")
            setFilms(pageCount)
        }
        //Кнопка "Предыдущая"
        binding.previousPage.isVisible = pageCount != 1
        binding.previousPage.setOnClickListener {
            pageCount--
            binding.page.text = pageCount.toString()
            setFilms(pageCount)
        }
        //Кнопка назад
        binding.backButton.setOnClickListener{
            ViewModel.goToNextFragment(this, LoginFragment.newInstance())
        }
        //Кнопка поиска
        binding.searchButton.setOnClickListener{
            pageCount = 1
            methodName = "getMoviesByKeyword"
            setFilms(pageCount)
        }
    }

    private fun setFilms(page: Int) {
        println("setFilms MoviesFragment")
        when(methodName){
            "getMovies" -> ViewModel.getMovies(page)
            "getMoviesByKeyword" -> ViewModel.getMoviesByKeyword(page, binding.searchEdit.text.toString())
        }
        if(!dbFlg) {
            ViewModel.movieList.observe(viewLifecycleOwner) { list ->
                list.body()?.let {
                    println("Вызов SetList")
                    adapter.setList(it.films)
                    binding.moviesRV.adapter = adapter
                }
            }
        } else {
            ViewModel.movieDB.observe(viewLifecycleOwner) {
                    println("Вызов SetList")
                    adapter.setList(it.films)
                    binding.moviesRV.adapter = adapter
                }
        }
    }
}


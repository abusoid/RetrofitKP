package com.example.retrofitkp.views.moviesPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retrofitkp.ViewModel
import com.example.retrofitkp.databinding.FragmentMoviesBinding
import com.example.retrofitkp.model.movie.MovieItem
import com.example.retrofitkp.views.detailMoviePage.DetailMovieFragment

class MoviesFragment : Fragment() {

    private val ViewModel by activityViewModels<ViewModel>()
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var adapter: MoviesAdapter
    //private lateinit var rv: RecyclerView
    private var page: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater)
        adapter = MoviesAdapter { item -> doClick(item) }
        with(binding) {
            moviesRV.layoutManager = GridLayoutManager(requireContext(), 2)
            println(adapter)
            moviesRV.adapter = adapter

        }
        return binding.root
    }

    companion object {

        fun newInstance() =
            MoviesFragment().apply {
            }
    }

    fun doClick(movie: MovieItem) {
        println(movie.nameRu)
        ViewModel.setFilms(movie)
        ViewModel.goToNextFragment(this, DetailMovieFragment.newInstance())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFilms(page)
        binding.page.text = page.toString()
    }

    fun setFilms(page: Int) {
        ViewModel.getFilms(page)
        ViewModel.movieList.observe(viewLifecycleOwner) { list ->
            list.body()?.let {
                adapter.setList(it.movies)
            }
        }
    }
}
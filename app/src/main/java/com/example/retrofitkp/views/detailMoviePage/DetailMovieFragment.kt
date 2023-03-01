package com.example.retrofitkp.views.detailMoviePage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.retrofitkp.R
import com.example.retrofitkp.ViewModel
import com.example.retrofitkp.databinding.FragmentDetailMovieBinding
import com.example.retrofitkp.databinding.FragmentMoviesBinding
import com.example.retrofitkp.views.loginPage.LoginFragment
import com.example.retrofitkp.views.moviesPage.MoviesFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie_layout.view.*

class DetailMovieFragment : Fragment() {
    private val ViewModel by activityViewModels<ViewModel>()
    private lateinit var binding: FragmentDetailMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailMovieBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButtonDetail.setOnClickListener {
            ViewModel.goToNextFragment(this, MoviesFragment.newInstance())
        }
        binding.nameRuDetail.text = ViewModel.movie.value?.nameRu
        binding.nameEnDetail.text = ViewModel.movie.value?.nameEn
        binding.descriptionDetail.text = ViewModel.movie.value?.description
        ViewModel.movie.value?.rating?.let { binding.ratingBar.setRating(it.toFloat()) }
        Picasso.get().load(ViewModel.movie.value?.posterUrl).into(binding.imageViewDetail)
    }
    companion object {
        fun newInstance() =
            DetailMovieFragment().apply {
            }
    }
}
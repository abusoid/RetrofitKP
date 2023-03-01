package com.example.retrofitkp.views.moviesPage

import android.annotation.SuppressLint
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkp.R
import com.example.retrofitkp.databinding.FragmentDetailMovieBinding
import com.example.retrofitkp.databinding.FragmentMoviesBinding
import com.example.retrofitkp.model.movie.MovieItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_movie.view.*
import kotlinx.android.synthetic.main.item_movie_layout.view.*


class MoviesAdapter(val adapterOnClick: (MovieItem) -> Unit) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    var movieList = emptyList<MovieItem>()

    class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        println("onCreateViewHolder MoviesAdapter")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_layout, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        println("onBindViewHolder MoviesAdapter")
        val currentItem = movieList[position]
        holder.itemView.movieName.text = currentItem.nameRu
        Picasso.get().load(currentItem.posterUrlPreview).into(holder.itemView.moviePreview)
        holder.itemView.moviePreview
        holder.itemView.setOnClickListener {
            adapterOnClick(movieList[position])
        }
    }



    override fun getItemCount(): Int {
        return movieList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<MovieItem>) {
        movieList = list
        notifyDataSetChanged()
    }

    /*fun getUrlDrawable(url: String): Drawable {
        val inputStream =  view.contentResolver.openInputStream(url)
        val drawable = Drawable.createFromStream(inputStream, url.toString())

    }*/


}
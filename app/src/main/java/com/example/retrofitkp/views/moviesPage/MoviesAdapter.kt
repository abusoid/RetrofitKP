package com.example.retrofitkp.views.moviesPage

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkp.R
import com.example.retrofitkp.model.movie.MovieItem
import kotlinx.android.synthetic.main.item_movie_layout.view.*


class MoviesAdapter(val adapterOnClick: (MovieItem) -> Unit) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    var movieList = emptyList<MovieItem>()

    class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_layout, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        //var preview: Drawable = getUrlDrawable(listMovies[position].posterUrlPreview)

        //holder.itemView.moviePreview.setImageDrawable(listMovies[position].posterUrlPreview)
        holder.itemView.movieName.text = movieList[position].nameRu
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
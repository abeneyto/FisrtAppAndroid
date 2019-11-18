package com.example.hello.ui.Search

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.hello.R
import com.example.hello.data.remote.Movie
import com.example.hello.ui.MovieDetail.MovieDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*


class MoviesAdapter() :
    RecyclerView.Adapter<CustomViewHolder>() {
    var movieList = listOf<Movie>()
    override fun getItemCount() = movieList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val item = from(parent.context).inflate(R.layout.movie_item, parent, false)
        return CustomViewHolder(item)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val movieItem = movieList[position]
        holder.moveToDetail(movieItem)
        val imagePath = "https://image.tmdb.org/t/p/original" + movieItem.poster_path
        movieItem.id
        holder.title.text = movieItem.title
        holder.originalTitle.text = movieItem.original_title
        holder.rate.text = movieItem.vote_average.toString()
        Picasso.get().load(imagePath).into(holder.image)
    }

    fun addMovies(listMovies: List<Movie>){
        movieList = listMovies
        notifyDataSetChanged()
    }
}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView = view.title_movie
    val originalTitle: TextView = view.original_title_movie
    val rate: TextView = view.rate
    val image: ImageView = view.imageMovie

    fun moveToDetail(movieItem: Movie) {
        view.setOnClickListener {
            val intent = Intent(view.context, MovieDetailActivity::class.java)
            intent.putExtra("id", movieItem.id)
            view.context.startActivity(intent)
        }
    }
}
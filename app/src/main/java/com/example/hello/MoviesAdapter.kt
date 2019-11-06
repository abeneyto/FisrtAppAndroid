package com.example.hello
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.hello.data.remote.Movie

class MoviesAdapter(private val movieList: List<Movie>) : RecyclerView.Adapter<StringListViewHolder>(){

    override fun getItemCount() = movieList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringListViewHolder {
        val item = from(parent.context).inflate(R.layout.movie_item, parent, false)
        return StringListViewHolder(item)
    }

    override fun onBindViewHolder(holder: StringListViewHolder, position: Int) {
        val movieItem = movieList[position]
        holder.title.text = movieItem.title
        holder.originalTitle.text = movieItem.original_title
        holder.rate.text = movieItem.vote_average.toString()
        //Picasso.get().load(movieItem.image).into(holder.image)

    }

}

class StringListViewHolder(val constraintLayout: View) : RecyclerView.ViewHolder(constraintLayout){
    val title: TextView = constraintLayout.findViewById(R.id.title_movie)
    val originalTitle: TextView = constraintLayout.findViewById(R.id.original_title_movie)
    val rate: TextView = constraintLayout.findViewById(R.id.rate)
}
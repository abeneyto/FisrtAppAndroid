package com.example.hello
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater.*
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.hello.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MoviesAdapter(private val movieList: List<Movie>) : RecyclerView.Adapter<StringListViewHolder>(){

    override fun getItemCount() = movieList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringListViewHolder {
        val item = from(parent.context).inflate(R.layout.movie_item, parent, false) as androidx.constraintlayout.widget.ConstraintLayout
        return StringListViewHolder(item)
    }

    override fun onBindViewHolder(holder: StringListViewHolder, position: Int) {
        val movieItem = movieList[position]
        holder.title.text = movieItem.title
        holder.originalTitle.text = movieItem.originalTitle
        holder.date.text = movieItem.date
        holder.rate.text = movieItem.voteAverage.toString()
        Picasso.get().load(movieItem.image).into(holder.image)

    }

}

class StringListViewHolder(val constraintLayout: androidx.constraintlayout.widget.ConstraintLayout) : RecyclerView.ViewHolder(constraintLayout){
    val image: ImageView = constraintLayout.findViewById(R.id.image_movie)
    val title: TextView = constraintLayout.findViewById(R.id.title_movie)
    val originalTitle: TextView = constraintLayout.findViewById(R.id.original_title_movie)
    val rate: TextView = constraintLayout.findViewById(R.id.rate)
    val date: TextView = constraintLayout.findViewById(R.id.date)



}
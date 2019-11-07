package com.example.hello
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.hello.data.remote.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesAdapter(private val movieList: List<Movie>,
                    private val onClickListener: (View, Movie) -> Unit
                    ) : RecyclerView.Adapter<StringListViewHolder>(){

    override fun getItemCount() = movieList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringListViewHolder {
        val item = from(parent.context).inflate(R.layout.movie_item, parent, false)
        return StringListViewHolder(item)
    }

    override fun onBindViewHolder(holder: StringListViewHolder, position: Int) {
        val movieItem = movieList[position]
        val imagePath = "https://image.tmdb.org/t/p/w500"
        holder.title.text = movieItem.title
        holder.originalTitle.text = movieItem.original_title
        holder.rate.text = movieItem.vote_average.toString()
       // Picasso.get().load(imagePath).into(holder.image)

        holder.itemView.setOnClickListener { view ->
            onClickListener.invoke(, movieItem)
        }
    }
}
class StringListViewHolder(val constraintLayout: View) : RecyclerView.ViewHolder(constraintLayout){
    val title: TextView = constraintLayout.title_movie
    val originalTitle: TextView = constraintLayout.original_title_movie
    val rate: TextView = constraintLayout.rate
   val image: ImageView = constraintLayout.imageMovie
}
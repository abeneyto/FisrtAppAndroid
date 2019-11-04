package com.example.hello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hello.model.Movie
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val movies = listOf(Movie(id = 1, date = "16/08/98", voteAverage = 9.7, title = "Pulp Fiction", originalTitle = "Pulp Fiction", gender = "Thiller", image = "https://m.media-amazon.com/images/M/MV5BMjI5MzgxMTQ4M15BMl5BanBnXkFtZTgwNjczMTk0NzE@._V1_UX477_CR0,0,477,268_AL_.jpg"),
            Movie(id = 1, date = "10/11/06", voteAverage = 9.9, title = "Cadena Perpetua", originalTitle = "The Shawshank Redemption", gender = "Suspense/Drama", image = "http://www.cajadeletras.es/wp-content/uploads/2019/05/Cadena_perpetua-245057314-large-e1558081854723.jpg"),
            Movie(id = 1, date = "10/11/06", voteAverage = 6.7, title = "Terminator", originalTitle = "Terminator", gender = "Accion", image = "https://cdn1us.denofgeek.com/sites/denofgeekus/files/styles/main_wide/public/2019/07/terminator-dark-fate-arnold-schwarzenegger-linda-hamilton-sdcc-den-of-geek-cover.jpg"))

        // LinearLayoutManager is de manager who manage the RecyclerView, not the items
        movies_reciclerview.layoutManager = LinearLayoutManager(this)
        movies_reciclerview.adapter = MoviesAdapter(movies)
    }
}

package com.example.hello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hello.data.remote.Movie
//magic import so dont find byId
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    val movie = Movie(
        id = 1,
        title = "Pulp Fiction 2",
        original_title = "Pulp Fiction 2",
        vote_average = 8.5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        with(movie) {
            movieTitle.text = title
            movieRate.text = vote_average.toString()
        }
    }
}

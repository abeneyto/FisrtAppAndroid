package com.example.hello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hello.model.Movie
import com.squareup.picasso.Picasso
//magic import so dont find byId
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    val movie = Movie(
        id = 1,
        title = "Pulp Fiction 2",
        originalTitle = "Pulp Fiction 2",
        voteAverage = 8.5,
        date = "23/08/1999",
        gender = "Comedy",
        image = "https://cdn.onebauer.media/one/empire-tmdb/films/680/images/mte63qJaVnoxkkXbHkdFujBnBgd.jpg?quality=50&width=1800&ratio=16-9&resizeStyle=aspectfill&format=jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        with(movie) {
            movieTitle.text = title
            movieYear.text = date
            movieGenre.text = gender
            movieRate.text = voteAverage.toString()
            Picasso.get().load(image).into(movieImage)

        }


    }

}

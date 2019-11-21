package com.example.hello.ui.MovieDetail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hello.R
import com.example.hello.data.local.DatabaseFactory
import com.example.hello.Model.Favorite
import com.example.hello.Model.MovieCrew
import com.example.hello.Model.MovieDetail
import com.example.hello.Model.MovieDirector
import com.example.hello.data.local.RoomDatabaseRepository
import com.example.hello.data.remote.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity(), MovieDetailView {

    private lateinit var presenter: MovieDetailPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        presenter = MovieDetailPresenter(
            this,
            RoomDatabaseRepository(DatabaseFactory.get(this)),
            RetrofitRemoteRepository(RetrofitFactory.makeRetrofitService())
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val id: Int = intent.getIntExtra("id", 1)
        presenter.dataDetailMovies(id)
    }

    override fun showData(
        body: MovieDetail,
        bodyCast: MovieCrew
    ) {
        movieDescription.text = body!!.overview
        val genre = body!!.genres
        movieTitle.text = body!!.title
        movieYear.text = body!!.release_date
        movieRate.text = body!!.vote_average.toString()
        val imagePath =
            "https://image.tmdb.org/t/p/original" + body!!.backdrop_path
        val strGenrs = genre.take(2).joinToString(", ") { it.name }
        movieGenre.text = strGenrs
        val crew = bodyCast.crew
        val cast = bodyCast.cast
        val strCast = cast.take(3).joinToString(", ") { it.name }
        movieCast.text = strCast
        val director: MovieDirector = crew.first { d -> d.job.equals("Director") }
        movieDirector.text = director.name
        Picasso.get().load(imagePath).into(movieImage)

    }

    override fun showError(errorText: String) {
        Toast.makeText(
            this@MovieDetailActivity,
            errorText,
            Toast.LENGTH_LONG
        )
    }

    override fun favBtnSelected(favorite: Favorite) {
        btnFavorites.setImageResource(R.drawable.image_full_star)
        btnFavorites.setOnClickListener {
            presenter.deleteFromFavorites(favorite)
        }
    }

    override fun favBtnNonSelected(favorite: Favorite) {
        btnFavorites.setImageResource(R.drawable.image_empty_star)
        btnFavorites.setOnClickListener {
            presenter.addToFavorite(favorite)
        }
    }
}

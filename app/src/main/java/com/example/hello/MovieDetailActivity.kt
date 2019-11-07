package com.example.hello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.hello.data.remote.MovieDirector
import com.example.hello.data.remote.RetrofitFactory
import com.squareup.picasso.Picasso
//magic import so dont find byId
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val id: Int = intent.getIntExtra("id", 1)
        dataDetailMovies(id)
    }

    private fun dataDetailMovies(id: Int) {
        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val responseMovie = service.getMovieDetail(id.toString(), apiKey)
            val responseCast = service.getMovieCredits(id.toString(), apiKey)

            Log.e("MovieCredits", responseCast.toString())
            withContext(Dispatchers.Main) {
                try {
                    if (responseMovie.isSuccessful && responseCast.isSuccessful) {
                        movieDescription.text = responseMovie.body()!!.overview
                        val genre = responseMovie.body()!!.genres
                        movieTitle.text = responseMovie.body()!!.title
                        movieYear.text = responseMovie.body()!!.release_date
                        movieRate.text = responseMovie.body()!!.vote_average.toString()
                        val imagePath =
                            "https://image.tmdb.org/t/p/original" + responseMovie.body()!!.backdrop_path
                        val strGenrs = genre.take(2).joinToString(", ") { it.name }
                        movieGenre.text = strGenrs
                        val crew = responseCast.body()!!.crew
                        val cast = responseCast.body()!!.cast
                        val strCast = cast.take(3).joinToString(", ") { it.name }
                        movieCast.text = strCast

                        val director: MovieDirector = crew.first { d -> d.job.equals("Director") }
                        movieDirector.text = director.name
                        Picasso.get().load(imagePath).into(movieImage)
                    } else {
                        Toast.makeText(
                            this@MovieDetailActivity,
                            "Error: ${responseMovie.code()}",
                            Toast.LENGTH_LONG
                        )
                    }
                } catch (e: HttpException) {
                    Toast.makeText(
                        this@MovieDetailActivity,
                        "Exception ${e.message}",
                        Toast.LENGTH_LONG
                    )
                } catch (e: Throwable) {
                    Toast.makeText(
                        this@MovieDetailActivity,
                        "Ooops: Something else went wrong",
                        Toast.LENGTH_LONG
                    )
                }
            }
        }
    }
}

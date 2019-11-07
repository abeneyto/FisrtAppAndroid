package com.example.hello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.hello.data.remote.Genres
import com.example.hello.data.remote.Movie
import com.example.hello.data.remote.RetrofitFactory
import com.squareup.picasso.Picasso
//magic import so dont find byId
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import kotlin.math.log

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val id:Int = intent.getIntExtra("id", 1)
        dataDetailMovies(id)
    }
    private fun dataDetailMovies(id: Int){
        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getMovieDetail(id.toString(), apiKey)
            //Log.e("MovieDetail",response.toString())

            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        movieDescription.text = response.body()!!.overview
                        val genre = response.body()!!.genres
                        movieGenre.text =  genre.get(0).name + ", " + genre.get(1).name



                        movieTitle.text = response.body()!!.title
                        movieYear.text = response.body()!!.release_date
                        movieRate.text = response.body()!!.vote_average.toString()
                        val imagePath = "https://image.tmdb.org/t/p/original"+response.body()!!.backdrop_path
                        Picasso.get().load(imagePath).into(movieImage)

                    } else {
                        //Toast.makeText("Error: ${response.code()}", Toast.LENGTH_LONG)
                    }
                } catch (e: HttpException) {
                   // Toast.makeText(this,"Exception ${e.message}", Toast.LENGTH_LONG)
                } catch (e: Throwable) {
                   // Toast.makeText(this,"Ooops: Something else went wrong", Toast.LENGTH_LONG)
                }
            }
        }
    }
}

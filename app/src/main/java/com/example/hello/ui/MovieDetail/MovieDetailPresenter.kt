package com.example.hello.ui.MovieDetail

import android.widget.Toast
import com.example.hello.data.remote.MovieCrew
import com.example.hello.data.remote.MovieDetail
import com.example.hello.data.remote.RetrofitFactory
import com.example.hello.ui.Main.apiKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class MovieDetailPresenter(val view: MovieDetailView) {
    fun dataDetailMovies(id: Int) {
        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val responseMovie = service.getMovieDetail(
                id.toString(),
                apiKey
            )
            val responseCast = service.getMovieCredits(
                id.toString(),
                apiKey
            )
            withContext(Dispatchers.Main) {
                try {
                    if (responseMovie.isSuccessful && responseCast.isSuccessful) {
                        val bodyMovie = responseMovie.body()!!
                        val bodyCast = responseCast.body()!!
                        view.showData(bodyMovie, bodyCast)

                    } else {
                        view.showError("Error: ${responseMovie.code()}")

                    }
                } catch (e: HttpException) {
                    view.showError("Exception ${e.message}")


                } catch (e: Throwable) {
                    view.showError("Ooops: Something else went wrong")
                }
            }
        }
    }
}

interface MovieDetailView {
    fun showData(
        body: MovieDetail,
        bodyCast: MovieCrew
    )

    fun showError(errorText: String)
}
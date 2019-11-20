package com.example.hello.ui.MovieDetail

import com.example.hello.data.local.LocalRepository
import com.example.hello.data.remote.Movie
import com.example.hello.data.remote.MovieCrew
import com.example.hello.data.remote.MovieDetail
import com.example.hello.data.remote.RetrofitFactory
import com.example.hello.ui.Main.apiKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class MovieDetailPresenter(val view: MovieDetailView, val localRepo: LocalRepository) {
    private lateinit var favorites: List<Movie>

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
            favorites = localRepo.isFavorite(id)
            withContext(Dispatchers.Main) {
                try {
                    if (responseMovie.isSuccessful && responseCast.isSuccessful) {
                        val body = responseMovie.body()!!
                        val bodyCast = responseCast.body()!!
                        val movie = Movie(
                            body!!.id,
                            body!!.vote_average,
                            body!!.title,
                            body!!.title,
                            body!!.poster_path
                        )
                        view.showData(body, bodyCast)
                        if (favorites.isEmpty()) {
                            view.favBtnNonSelected(movie)

                        } else {
                            view.favBtnSelected(movie)
                        }
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

    fun addToFavorite(
        movie: Movie
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            localRepo.insertFavMovie(movie)
            withContext(Dispatchers.Main) {
                view.favBtnSelected(movie)
            }
        }
    }

    fun deleteFromFavorites(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            localRepo.deleteFavMovie(movie.id)
            withContext(Dispatchers.Main) {
                view.favBtnNonSelected(movie)
            }
        }
    }
}


interface MovieDetailView {
    fun showData(
        body: MovieDetail,
        bodyCast: MovieCrew
    )

    fun favBtnSelected(movie: Movie)

    fun favBtnNonSelected(movie: Movie)

    fun showError(errorText: String)
}
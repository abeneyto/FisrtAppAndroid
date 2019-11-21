package com.example.hello.ui.MovieDetail

import com.example.hello.data.local.Favorite
import com.example.hello.data.local.LocalRepository
import com.example.hello.data.remote.MovieCrew
import com.example.hello.data.remote.MovieDetail
import com.example.hello.data.remote.RemoteRepository
import com.example.hello.data.remote.RetrofitFactory
import com.example.hello.ui.Main.apiKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class MovieDetailPresenter(
    val view: MovieDetailView,
    val localRepo: LocalRepository,
    val remoteRepo: RemoteRepository
) {
    private lateinit var favorites: List<Favorite>

    fun dataDetailMovies(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val responseMovie = remoteRepo.getMovieDetail(
                id.toString(),
                apiKey
            )
            val responseCast = remoteRepo.getMovieCredits(
                id.toString(),
                apiKey
            )
            favorites = localRepo.isFavorite(id)
            withContext(Dispatchers.Main) {
                val favorite = Favorite(
                    responseMovie.id,
                    responseMovie.vote_average,
                    responseMovie.title,
                    responseMovie.original_title,
                    responseMovie.poster_path
                )
                view.showData(responseMovie, responseCast)
                if (favorites.isEmpty()) {
                    view.favBtnNonSelected(favorite)

                } else {
                    view.favBtnSelected(favorite)
                }

            }
        }
    }

    fun addToFavorite(
        favorite: Favorite
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            localRepo.insertFavMovie(favorite)
            withContext(Dispatchers.Main) {
                view.favBtnSelected(favorite)
            }
        }
    }

    fun deleteFromFavorites(favorite: Favorite) {
        CoroutineScope(Dispatchers.IO).launch {
            localRepo.deleteFavMovie(favorite.id)
            withContext(Dispatchers.Main) {
                view.favBtnNonSelected(favorite)
            }
        }
    }
}

interface MovieDetailView {
    fun showData(
        body: MovieDetail,
        bodyCast: MovieCrew
    )

    fun favBtnSelected(favorite: Favorite)

    fun favBtnNonSelected(favorite: Favorite)

    fun showError(errorText: String)
}
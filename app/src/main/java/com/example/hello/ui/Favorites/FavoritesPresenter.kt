package com.example.hello.ui.Favorites

import com.example.hello.data.local.LocalRepository
import com.example.hello.data.remote.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesPresenter(val view: FavoritesView, val localRepo: LocalRepository) {
    fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            val movies = localRepo.getFavMovies()
            withContext(Dispatchers.Main) {
                view.showMovies(movies)
            }
        }
    }
}
    interface FavoritesView {
        fun showMovies(listMovie: List<Movie>)
    }

package com.example.hello.ui.Favorites

import com.example.hello.Model.Favorite
import com.example.hello.data.local.LocalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesPresenter(private val view: FavoritesView, private val localRepo: LocalRepository) {
    fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            val movies = localRepo.getFavMovies()
            withContext(Dispatchers.Main) {
                view.showMovies(movies)
            }
        }
    }

    fun deleteAll() {
        CoroutineScope(Dispatchers.IO).launch {
            localRepo.deleteAll()
            withContext(Dispatchers.Main) {
                init()
            }
        }
    }

    fun orderByDate() {
        CoroutineScope(Dispatchers.IO).launch {
            val movies = localRepo.getFavMoviesByCreated()
            withContext(Dispatchers.Main) {
                view.showMovies(movies)
            }
        }
    }

    fun orderByTitle() {
        CoroutineScope(Dispatchers.IO).launch {
            val movies = localRepo.getFavMoviesByTitle()
            withContext(Dispatchers.Main) {
                view.showMovies(movies)
            }
        }
    }
}

interface FavoritesView {
    fun showMovies(listMovie: List<Favorite>)
}

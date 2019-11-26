package com.example.hello.ui.Search

import com.example.hello.Model.Movie
import com.example.hello.data.remote.RemoteRepository
import com.example.hello.ui.Main.apiKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchPresenter(private val view: MovieSearchView, private val remoteRepo: RemoteRepository) {

    fun onChangeText(text: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val map = mapOf("api_key" to apiKey, "query" to text)
            val response = remoteRepo.getMovies(map)
            withContext(Dispatchers.Main) {
                view.showMovies(response.results)
            }
        }
    }
}

interface MovieSearchView {
    fun showMovies(movie: List<Movie>)
    fun showError(errorText: String)
}
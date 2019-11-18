package com.example.hello.ui.Search

import com.example.hello.data.remote.Movie
import com.example.hello.data.remote.RetrofitFactory
import com.example.hello.ui.Main.apiKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class SearchPresenter(val view: MovieSearchView) {

    fun onChangeText(text: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val service = RetrofitFactory.makeRetrofitService()
            val map = mapOf("api_key" to apiKey, "query" to text)
            val response = service.getMovies(map)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        view.showMovies(response.body()!!.results)
                    } else {
                        val str = "Error: ${response.code()}"
                        view.showError(str)
                    }
                } catch (e: HttpException) {
                    val str = "Exception ${e.message}"
                    view.showError(str)
                } catch (e: Throwable) {
                    val str = "Ooops: Something else went wrong"
                    view.showError(str)
                }
            }
        }
    }
}

interface MovieSearchView {
    fun showMovies(movie: List<Movie>)
    fun showError(errorText: String)
}
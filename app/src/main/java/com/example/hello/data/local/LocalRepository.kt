package com.example.hello.data.local

import com.example.hello.data.remote.Movie

interface LocalRepository{
    suspend fun getFavMovies(): List<Movie>
    suspend fun getFavMoviesByTitle(): List<Movie>
    suspend fun getFavMoviesByVotes(): List<Movie>
    suspend fun deleteFavMovie(id:Int)
    suspend fun isFavorite(id:Int): List<Movie>
    suspend fun insertFavMovie(movie: Movie)
}
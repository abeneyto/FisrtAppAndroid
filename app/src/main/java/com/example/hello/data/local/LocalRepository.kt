package com.example.hello.data.local

import com.example.hello.Model.Favorite

interface LocalRepository {
    suspend fun getFavMovies(): List<Favorite>
    suspend fun getFavMoviesByTitle(): List<Favorite>
    suspend fun getFavMoviesByCreated(): List<Favorite>
    suspend fun deleteFavMovie(id: Int)
    suspend fun isFavorite(id: Int): List<Favorite>
    suspend fun insertFavMovie(favorite: Favorite)
    suspend fun deleteAll()
}
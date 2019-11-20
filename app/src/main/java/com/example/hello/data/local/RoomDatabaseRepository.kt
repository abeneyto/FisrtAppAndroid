package com.example.hello.data.local

import com.example.hello.data.remote.Movie


open class RoomDatabaseRepository(private val database: AppDatabase) : LocalRepository {


    private val daoRepository = database.movieDao()
    override suspend fun isFavorite(id: Int): List<Movie> {
        var favorites: List<Movie> = listOf()
        favorites= daoRepository.isFavorite(id)
        return favorites
    }
    override suspend fun deleteFavMovie(id: Int) {
        daoRepository.deleteFeavorite(id)
    }

    override suspend fun insertFavMovie(movie: Movie) {
        daoRepository.insertMovie(movie)
    }

    override suspend fun getFavMoviesByVotes(): List<Movie> {
        var movies: List<Movie> = listOf()
            movies = daoRepository.getOrderByOverage()
        return movies
    }

    override suspend fun getFavMoviesByTitle(): List<Movie> {
        var movies: List<Movie> = listOf()
            movies = daoRepository.getOrderByTitle()
        return movies
    }

    override suspend fun getFavMovies(): List<Movie> {
        var movies: List<Movie> = listOf()
        movies = daoRepository.getMovies()
        return movies
    }
}

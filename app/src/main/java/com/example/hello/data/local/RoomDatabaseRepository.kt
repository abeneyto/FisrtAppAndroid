package com.example.hello.data.local

open class RoomDatabaseRepository(private val database: AppDatabase) : LocalRepository {
    private val daoRepository = database.movieDao()

    override suspend fun deleteAll() {
        daoRepository.deleteAll()
    }

    override suspend fun isFavorite(id: Int): List<Favorite> {
        var favorites: List<Favorite> = listOf()
        favorites = daoRepository.isFavorite(id)
        return favorites
    }
    override suspend fun deleteFavMovie(id: Int) {
        daoRepository.deleteFavorite(id)
    }

    override suspend fun insertFavMovie(movie: Favorite) {
        daoRepository.insertMovie(movie)
    }

    override suspend fun getFavMoviesByCreated(): List<Favorite> {
        var movies: List<Favorite> = listOf()
            movies = daoRepository.getOrderByCreated()
        return movies
    }

    override suspend fun getFavMoviesByTitle(): List<Favorite> {
        var movies: List<Favorite> = listOf()
            movies = daoRepository.getOrderByTitle()
        return movies
    }

    override suspend fun getFavMovies(): List<Favorite> {
        var movies: List<Favorite> = listOf()
        movies = daoRepository.getMovies()
        return movies
    }
}

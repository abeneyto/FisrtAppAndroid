package com.example.hello.data.remote

interface RemoteRepository {

    suspend fun getMovies(map: Map<String, String>): Result

    suspend fun getMovieDetail(id: String?, key: String): MovieDetail

    suspend fun getMovieCredits(id: String?, key: String): MovieCrew
}
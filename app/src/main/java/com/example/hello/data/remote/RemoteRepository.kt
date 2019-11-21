package com.example.hello.data.remote

import com.example.hello.Model.MovieCrew
import com.example.hello.Model.MovieDetail
import com.example.hello.Model.Result
import com.example.hello.Model.User

interface RemoteRepository {
    suspend fun login(username: String, password: String): User?

    suspend fun getMovies(map: Map<String, String>): Result

    suspend fun getMovieDetail(id: String?, key: String): MovieDetail

    suspend fun getMovieCredits(id: String?, key: String): MovieCrew
}
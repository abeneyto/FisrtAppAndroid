package com.example.hello.data.remote
import android.accounts.NetworkErrorException


class RetrofitRemoteRepository(val movieApi: APIService) : RemoteRepository {
    override suspend fun getMovies(map: Map<String, String>): Result {
        val response = movieApi.getMovies(map)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw NetworkErrorException()
        }
    }

    override suspend fun getMovieDetail(id: String?, key: String): MovieDetail {
        val response = movieApi.getMovieDetail(id, key)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw NetworkErrorException()
        }
    }

    override suspend fun getMovieCredits(id: String?, key: String): MovieCrew {
        val response = movieApi.getMovieCredits(id, key)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw NetworkErrorException()
        }
    }
}

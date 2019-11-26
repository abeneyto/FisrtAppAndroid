package com.example.hello.data.remote
import android.accounts.NetworkErrorException
import android.util.Log
import com.example.hello.Model.MovieCrew
import com.example.hello.Model.MovieDetail
import com.example.hello.Model.Result
import com.example.hello.Model.User


class RetrofitRemoteRepository(private val movieApi: APIService) : RemoteRepository {

    override suspend fun login(username: String, password: String): User? {
        if (username.equals("root") && password.equals("1234")) {
            return User(username, password)
        } else {
            return null
        }
    }
    override suspend fun getMovies(map: Map<String, String>): Result {
        val response = movieApi.getMovies(map)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            Log.e("Antes del fallo","")
            throw NetworkErrorException()
        }
    }

    override suspend fun getMovieDetail(id: String?, key: String): MovieDetail {
        val response = movieApi.getMovieDetail(id, key)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            Log.e("Antes del fallo","")
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

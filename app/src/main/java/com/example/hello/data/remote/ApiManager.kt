package com.example.hello.data.remote

import com.example.hello.Model.MovieCrew
import com.example.hello.Model.MovieDetail
import com.example.hello.Model.Result
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface APIService {
    @GET("search/movie")
    suspend fun getMovies(@QueryMap map:Map<String, String>): Response<Result>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id")id:String?, @Query("api_key")key:String): Response<MovieDetail>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id")id:String?, @Query("api_key")key:String): Response<MovieCrew>
}
object RetrofitFactory {
    const val BASE_URL = "https://api.themoviedb.org/3/"

    fun makeRetrofitService(): APIService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(APIService::class.java)
    }
}
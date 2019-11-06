package com.example.hello.data.remote



data class Movie(val id:Int, val vote_average:Double, val title:String, val original_title: String)

data class MovieDetail(val id:Int, val backdrop_path:String, val overview:String, val title:String, val vote_average: Double, val genres: List<Genres>, val release_date:String)

data class MovieCrew(val id:Int, val cast: List<MovieCast>, val crew: List<MovieDirector>)

data class MovieCast(val name:String)

data class MovieDirector(val name:String)

data class Result(val page:Int, val results:List<Movie>)

data class Genres(val name:String, val job:String)

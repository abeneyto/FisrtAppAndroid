package com.example.hello.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(
    @PrimaryKey
    val id:Int,
    val vote_average:Double,
    val title:String,
    val original_title: String,
    val poster_path: String,
    val created: Long = System.currentTimeMillis()
)
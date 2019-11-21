package com.example.hello.Model

data class User(
    val username: String,
    val password: String,
    val created: Long = System.currentTimeMillis()
)
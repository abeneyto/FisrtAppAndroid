package com.example.hello.data.local

import com.example.hello.Model.User

interface LocalPreferencesRepository {
    suspend fun getLoggedUser(): User?
    suspend fun setLoggedUser(user: User)
    suspend fun deleteLoggedUser()
}
package com.example.hello.data.local

import android.content.SharedPreferences
import com.example.hello.Model.User

class PreferenceRepository(private val sharedPreferences: SharedPreferences) :
    LocalPreferencesRepository {
    val usernameKey = "username"

    val passwordKey = "password"
    override suspend fun getLoggedUser(): User? {
        val username = sharedPreferences.getString(usernameKey, null)
        val password = sharedPreferences.getString(passwordKey, null)
        if (username != null && password != null) {
            return User(username, password)
        } else {
            return null
        }
    }

    override suspend fun setLoggedUser(user: User) {
        val editor = sharedPreferences.edit()
        editor.putString(usernameKey, user.username)
        editor.putString(passwordKey, user.password)
        editor.apply()
    }

    override suspend fun deleteLoggedUser() {
        sharedPreferences.edit().clear().apply()
    }
}
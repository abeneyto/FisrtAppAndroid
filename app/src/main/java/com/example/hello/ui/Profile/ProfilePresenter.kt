package com.example.hello.ui.Profile

import com.example.hello.data.local.LocalPreferencesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date
import java.text.SimpleDateFormat


class ProfilePresenter(
    private val view: ProfileView,
    private val localRepository: LocalPreferencesRepository
) {
    fun logoutClicked() {
        CoroutineScope(Dispatchers.IO).launch {
            localRepository.deleteLoggedUser()
            withContext(Dispatchers.Main) {
                view.goToLogin()
            }
        }
    }

    fun getUser() {
        CoroutineScope(Dispatchers.IO).launch {
            val user = localRepository.getLoggedUser()
            withContext(Dispatchers.Main) {
                val date = Date(user!!.created)
                val format = SimpleDateFormat("yyyy.MM.dd HH:mm")

                view.setUserLabel(user!!.username, format.format(date))
            }
        }
    }
}

interface ProfileView {
    fun goToLogin()
    fun setUserLabel(username: String, created: String)
}
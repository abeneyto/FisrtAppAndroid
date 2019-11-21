package com.example.hello.ui.Login


import com.example.hello.Model.User
import com.example.hello.data.local.LocalPreferencesRepository
import com.example.hello.data.remote.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginPresenter(
    private val view: LoginView,
    private val localRepository: LocalPreferencesRepository,
    private val remoteRepository: RemoteRepository
) {

    fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            val loggedUser = localRepository.getLoggedUser()
            if (loggedUser != null) {
                view.goToSearch()
            }
        }
    }

    fun onLoginClicked(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            val emptyList = mutableListOf<String>()
            if (username.isEmpty()) {
                emptyList.add("username")
            }
            if (password.isEmpty()) {
                emptyList.add("password")
            }
            view.showFieldRequiredError(emptyList)
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            val loggedUser = remoteRepository.login(username, password)
            if (loggedUser != null) {
                localRepository.setLoggedUser(User(username, password))
                withContext(Dispatchers.Main) {
                    view.showLoginSuccessful()
                }
            } else {
                withContext(Dispatchers.Main) {
                    view.showLoginError()
                }
            }
        }
    }

    fun onClearClicked() {
        view.clearFields()
    }

}

interface LoginView {
    fun showLoginSuccessful()
    fun showLoginError()
    fun showFieldRequiredError(emptyList: List<String>)
    fun clearFields()
    fun goToSearch()
}
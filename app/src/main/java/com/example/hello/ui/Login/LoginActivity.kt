package com.example.hello.ui.Login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hello.R
import com.example.hello.data.local.PreferenceRepository
import com.example.hello.data.remote.RetrofitFactory
import com.example.hello.data.remote.RetrofitRemoteRepository
import com.example.hello.ui.Main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val localRepository =
            PreferenceRepository(
                getSharedPreferences(
                    "login_preference",
                    Context.MODE_PRIVATE
                )
            )
        val remoteRepository = RetrofitRemoteRepository(RetrofitFactory.makeRetrofitService())
        val presenter = LoginPresenter(this, localRepository, remoteRepository)

        loginBtn.setOnClickListener {
            val username = usernameTxt.text.toString()
            val password = passwordTxt.text.toString()
            presenter.onLoginClicked(username, password)
        }

        clearBtn.setOnClickListener {
            presenter.onClearClicked()
        }

        presenter.init()
    }

    override fun showLoginSuccessful() {
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
        goToSearch()
    }

    override fun showLoginError() {
        Toast.makeText(this, "Login error", Toast.LENGTH_SHORT).show()
        clearFields()
    }

    override fun showFieldRequiredError(emptyList: List<String>) {
        Toast.makeText(
            this,
            "The fields ${emptyList.joinToString()} are required",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun clearFields() {
        usernameTxt.text.clear()
        passwordTxt.text.clear()
    }

    override fun goToSearch() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

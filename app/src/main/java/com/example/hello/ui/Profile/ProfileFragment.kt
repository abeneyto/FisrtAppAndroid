package com.example.hello.ui.Profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.hello.data.local.PreferenceRepository
import com.example.hello.ui.Login.LoginActivity
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileFragment : Fragment(), ProfileView {

    private lateinit var presenter: ProfilePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(com.example.hello.R.layout.perfil_fragment, container, false)
        // Inflate the layout for this fragment
        val localRepository =
            PreferenceRepository(
                this.activity!!.getSharedPreferences(
                    "login_preference",
                    Context.MODE_PRIVATE
                )
            )
        presenter = ProfilePresenter(this, localRepository)
        presenter.getUser()


        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            com.example.hello.R.id.item_logOut -> presenter.logoutClicked()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(com.example.hello.R.menu.menu_profile, menu)
    }

    override fun goToLogin() {
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        this.activity!!.finish()
    }

    override fun setUserLabel(username: String, created: String) {
        textName.text = username
        textDateContent.text = created
    }


}
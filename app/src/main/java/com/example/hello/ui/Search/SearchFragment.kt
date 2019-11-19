package com.example.hello.ui.Search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.busqueda_fragment.view.*
import com.example.hello.R
import com.example.hello.data.remote.Movie


class SearchFragment : Fragment(), MovieSearchView {

    private lateinit var adapter: MoviesAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var textToSearch: EditText
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.busqueda_fragment, container, false)
        val presenter = SearchPresenter(this)
        adapter = MoviesAdapter()
        recycler = view.movies_reciclerview
        recycler.adapter = adapter
        textToSearch = view.searchText
        recycler.layoutManager = LinearLayoutManager(this.context)

        textToSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                presenter.onChangeText(textToSearch.text.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        return view
    }
    override fun showMovies(listMovie: List<Movie>) {
        adapter.addMovies(listMovie)
    }

    override fun showError(errorText: String) {
        Toast.makeText(activity, errorText, Toast.LENGTH_LONG)
    }

}
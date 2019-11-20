package com.example.hello.ui.Favorites

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hello.R
import com.example.hello.data.local.DatabaseFactory
import com.example.hello.data.local.RoomDatabaseRepository
import com.example.hello.data.remote.Movie
import com.example.hello.ui.Search.MoviesAdapter
import kotlinx.android.synthetic.main.favoritos_fragment.view.*

/**
 * A simple [Fragment] subclass.
 */
class FavoritesFragment : Fragment(), FavoritesView {
    private lateinit var adapter: MoviesAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var presenter: FavoritesPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.favoritos_fragment, container, false)
        adapter = MoviesAdapter()
        recycler = view.recyclerViewFavorites
        recycler.layoutManager =
            LinearLayoutManager(view.context)      // Inflate the layout for this fragment
        recycler.adapter = adapter
        presenter =
            FavoritesPresenter(this, RoomDatabaseRepository(DatabaseFactory.get(activity!!)))
        presenter.init()
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_favorites, menu)
    }

    override fun showMovies(listMovie: List<Movie>) {
        adapter.addMovies(listMovie)
        if (listMovie.isEmpty()) {
            Log.e("Respuesta", "ta vacio")
        }
    }
}
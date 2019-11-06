package com.example.hello

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hello.data.remote.Movie
import com.example.hello.data.remote.RetrofitFactory
import kotlinx.android.synthetic.main.busqueda_fragment.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * A simple [Fragment] subclass.
 */
class BusquedaFragment : Fragment() {
lateinit var recycler: RecyclerView
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        var view =inflater.inflate(R.layout.busqueda_fragment, container, false)

        recycler = view.movies_reciclerview
        recycler.layoutManager = LinearLayoutManager(this.context)
        searchMovies("pu")
        return view
    }
    private fun searchMovies(textoABuscar: String){
        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val map = mapOf("api_key" to apiKey, "query" to textoABuscar)
            val response = service.getMovies(map)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        recycler.adapter = MoviesAdapter(response.body()!!.results)
                    } else {
                        Toast.makeText(activity,"Error: ${response.code()}", Toast.LENGTH_LONG)
                    }
                } catch (e: HttpException) {
                    Toast.makeText(activity,"Exception ${e.message}", Toast.LENGTH_LONG)
                } catch (e: Throwable) {
                    Toast.makeText(activity,"Ooops: Something else went wrong", Toast.LENGTH_LONG)
                }
            }
        }
    }



}
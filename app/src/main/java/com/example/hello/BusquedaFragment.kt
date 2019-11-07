package com.example.hello

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hello.data.remote.Movie
import com.example.hello.data.remote.RetrofitFactory
import kotlinx.android.synthetic.main.busqueda_fragment.*
import kotlinx.android.synthetic.main.busqueda_fragment.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import android.view.View.OnFocusChangeListener



/**
 * A simple [Fragment] subclass.
 */
class BusquedaFragment : Fragment() {
private lateinit var recycler: RecyclerView
private  lateinit var textToSearch : EditText
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        var view =inflater.inflate(R.layout.busqueda_fragment, container, false)
        recycler = view.movies_reciclerview
        textToSearch = view.searchText
        recycler.layoutManager = LinearLayoutManager(this.context)

        textToSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                searchMovies(textToSearch.text.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        return view
    }
    private fun searchMovies(searchText: String){
        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val map = mapOf("api_key" to apiKey, "query" to searchText)
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
    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

    }
}
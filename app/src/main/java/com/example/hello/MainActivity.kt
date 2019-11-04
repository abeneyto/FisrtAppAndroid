package com.example.hello

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var countText: TextView
    private lateinit var myButton: Button
    private lateinit var myButtonProfile: Button
    private lateinit var myButtonMovie: Button
    private lateinit var myButtonMovieList: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countText = findViewById(R.id.helloText)
        myButton = findViewById<Button>(R.id.buton1)
        myButtonProfile = findViewById<Button>(R.id.button_profile)
        myButtonMovie = findViewById<Button>(R.id.button_movie)
        myButtonMovieList = findViewById<Button>(R.id.button_movielist)

        var count: Int = 0;

        myButton.setOnClickListener {
            count++
            incrementText(count)
            val message = "Ha ganado "
            Toast.makeText(MainActivity@ this, message, Toast.LENGTH_SHORT).show()
        }
        myButtonProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("image", R.drawable.guti)
            intent.putExtra("name", "Guti")
            intent.putExtra("birthdate", "12/03/1975")
            intent.putExtra("city", "Madrid")
            intent.putExtra("history", "This is the history of Jose Maria Gutierrez, Guti." +
                    "/n" + "Exjugador del Real Madrid, que ahora desarrolla su carrera como entrenador.")
            startActivity(intent)
        }
        myButtonMovie.setOnClickListener {
            val intent = Intent(this, MovieDetailActivity::class.java)
            startActivity(intent)
        }
        myButtonMovieList.setOnClickListener {
            val intent = Intent(this, MovieListActivity::class.java)
            startActivity(intent)
        }

    }

    fun incrementText(pressedTimes: Int){
        countText.text = "Button pressed $pressedTimes"
    }
}

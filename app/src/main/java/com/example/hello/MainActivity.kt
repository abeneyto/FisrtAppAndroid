package com.example.hello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var countText: TextView
    private lateinit var myButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countText = findViewById(R.id.helloText)
        myButton = findViewById<Button>(R.id.buton1)
        var count: Int = 0;

        myButton.setOnClickListener {
            count++
            val message = getString(R.string.button)
            incrementText(count)
            Toast.makeText(MainActivity@ this, message, Toast.LENGTH_SHORT).show()
        }

    }
    fun incrementText(pressedTimes: Int){
        countText.text = "Button pressed $pressedTimes"
    }
}

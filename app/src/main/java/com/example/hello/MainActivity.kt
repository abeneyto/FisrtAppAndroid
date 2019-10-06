package com.example.hello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countText: TextView = findViewById(R.id.helloText)

        val myButton = findViewById<Button>(R.id.buton1)
        var count : Int = 0;

        myButton.setOnClickListener {
            count++
            val message = getString(R.string.button)
            countText.text = "Clicks on button: " + count.toString()

            Toast.makeText(MainActivity@this, message, Toast.LENGTH_SHORT).show()
        }
    }
}

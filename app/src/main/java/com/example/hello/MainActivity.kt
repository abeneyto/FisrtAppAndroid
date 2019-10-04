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

        val heloText: TextView = findViewById(R.id.helloText)
        heloText.text = "Hello UTAD"

        val muButton = findViewById<Button>(R.id.buton1)
        muButton.setOnClickListener {
            val message = getString(R.string.button)
            Toast.makeText(MainActivity@this, message, Toast.LENGTH_SHORT).show()
        }
    }
}

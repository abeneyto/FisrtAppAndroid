package com.example.hello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {
    private lateinit var lblCity: TextView
    private lateinit var lblName: TextView
    private lateinit var lblBirthDate: TextView
    private lateinit var txtHistory: TextView
    private lateinit var imageProfile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        lblCity = findViewById<TextView>(R.id.text_city_content)
        lblName = findViewById<TextView>(R.id.text_name)
        lblBirthDate = findViewById<TextView>(R.id.text_date_content)
        imageProfile = findViewById<ImageView>(R.id.image_profile)
        txtHistory = findViewById<TextView>(R.id.text_history)


        val city = intent.extras?.getString("city")
        val name = intent.extras?.getString("name")
        val birthDate = intent.extras?.getString("birthdate")
        val history = intent.extras?.getString("history")
        val image = intent.extras?.getInt("image")

        lblCity.text = city
        lblName.text = name
        lblBirthDate.text = birthDate
        imageProfile.setImageResource(image!!)
        txtHistory.text = history


    }

}

package com.example.hello

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val apiKey = "b77d905a8b9dd24049c8817705063b08"

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController1 = findNavController(R.id.favoritosFragment)
        val appBarConfiguration = AppBarConfiguration(navController1.graph)
        // para los titulos arriba
        setupActionBarWithNavController(navController1, appBarConfiguration)
        bottomNavigationView2.setupWithNavController(navController1)
}

}

package com.example.apicalltask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.example.apicalltask.databinding.HomeScreenBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeScreenActivity : AppCompatActivity() {
    private lateinit var binding: HomeScreenBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        // Set up navigation with BottomNavigationView
        bottomNavigationView.setupWithNavController(navController)

    }
}



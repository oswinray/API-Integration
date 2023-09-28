package com.example.apicalltask

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.example.apicalltask.databinding.HomeScreenBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeScreenActivity : AppCompatActivity() {
    private lateinit var binding: HomeScreenBinding
    private lateinit var navController: NavController
    private lateinit var connectionLiveData: ConnectionLiveData
    private lateinit var statusTextView: TextView
    private var shouldShowWelcome = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        statusTextView = findViewById(R.id.statusTextView)
        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this) { isNetworkAvailable ->
            isNetworkAvailable?.let {
                updateUI(it, statusTextView)
            }
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        // Set up navigation with BottomNavigationView
        bottomNavigationView.setupWithNavController(navController)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateUI(isNetworkAvailable: Boolean, statusTextView: TextView) {
        if (!isNetworkAvailable) {
            // Network is not available, make the TextView visible and set text in red
            statusTextView.visibility = View.VISIBLE
            statusTextView.text = "<font color='#FF0000'>Please check your connectivity</font>"
            shouldShowWelcome = true // Allow showing "Welcome back" next time
        } else if (shouldShowWelcome) {
            // User just came online and the flag is set, show "Welcome back" for 5 seconds in green
            statusTextView.visibility = View.VISIBLE
            statusTextView.text = "<font color='#00FF00'>Welcome back</font>"

            // Hide the TextView after 5 seconds
            Handler().postDelayed({
                statusTextView.visibility = View.GONE
            }, 5000) // 5000 milliseconds = 5 seconds

            shouldShowWelcome = false // Reset the flag
        } else {
            // Hide the TextView when the app is initially opened
            statusTextView.visibility = View.GONE
        }

        // Apply HTML formatting to the TextView text
        statusTextView.text = Html.fromHtml(statusTextView.text.toString(), Html.FROM_HTML_MODE_LEGACY)
    }





}



package com.example.apicalltask

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



import com.example.apicalltask.databinding.ActivitySplashScreenBinding  // Import the generated binding class

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding  // Declare a binding variable
    private val handler = Handler(Looper.getMainLooper())
    private val checkNetworkInterval = 2000L // Check for network connectivity every 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)  // Initialize the binding
        setContentView(binding.root)  // Use the root view from the binding

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // Load the fade-in animation from the XML resource.
        val fadeAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.side_slide)

        // Find the ImageView or other views you want to animate.
        val backgroundImage: ImageView = binding.SplashScreenImage

        // Set the animation to the view.
        backgroundImage.startAnimation(fadeAnimation)

        // Find the TextView using View Binding
        val noNetworkTextView: TextView = binding.noNetworkTextView

        // Start checking for network connectivity
        checkNetworkConnectivity()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Remove any pending callbacks to avoid leaks
        handler.removeCallbacksAndMessages(null)
    }

    private fun checkNetworkConnectivity() {
        if (isNetworkAvailable()) {
            // Network is available, start HomeScreenActivity
            val intent = Intent(this, HomeScreenActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // Network is not available, show message and check again
            binding.noNetworkTextView.text = resources.getString(R.string.network_lost)
            handler.postDelayed({
                checkNetworkConnectivity()
            }, checkNetworkInterval)
        }
    }


    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}



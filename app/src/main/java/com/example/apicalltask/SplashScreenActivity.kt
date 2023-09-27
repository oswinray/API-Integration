package com.example.apicalltask

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.SyncStateContract
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.apicalltask.HomeScreenActivity
import com.example.apicalltask.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // Load the fade-in animation from the XML resource.
        val fadeAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.side_slide)

        // Find the ImageView or other views you want to animate.
        val backgroundImage: ImageView = findViewById(R.id.SplashScreenImage)

        // Set the animation to the view.
        backgroundImage.startAnimation(fadeAnimation)

        // Check network availability and proceed accordingly.
        if (isNetworkAvailable()) {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, HomeScreenActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000) // Delay for 1 second (1000 milliseconds)
        } else {
            showNoNetworkError()
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    private fun showNoNetworkError() {
        // Display a message to the user or show a dialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("No Network Connection")
        builder.setMessage("Please check your network connection and try again.")
        builder.setPositiveButton("Retry") { _, _ ->
            recreate() // Restart the activity to check for network again
        }
        builder.setNegativeButton("Exit") { _, _ ->
            finish() // Exit the app if the user chooses to
        }
        val dialog = builder.create()
        dialog.show()// 3000 is the delayed time in milliseconds.
    }
}

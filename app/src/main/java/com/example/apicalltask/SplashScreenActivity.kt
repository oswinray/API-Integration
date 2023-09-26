package com.example.apicalltask

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.SyncStateContract
import androidx.appcompat.app.AlertDialog

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        if (isNetworkAvailable()) {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, HomeScreenActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000)
        } else {
            // Handle the case when there's no network connection
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
        dialog.show()
    }
}

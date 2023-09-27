package com.example.apicalltask

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class FullScreenImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)
        val fullScreenImageView = findViewById<ImageView>(R.id.fullScreenImageView)
        val imageUrl = intent.getStringExtra("image_url")

        // Load the image into the ImageView using Glide
        Glide.with(this)
            .load(imageUrl)
            .fitCenter()
            .into(fullScreenImageView)

        // Hide the status bar and action bar for full-screen display
        window.decorView.apply {
            systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    )
        }
    }
}

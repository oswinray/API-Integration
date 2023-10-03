package com.example.apicalltask

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.apicalltask.databinding.ActivityFullScreenImageBinding
import kotlin.math.max
import kotlin.math.min

class FullScreenImageActivity : AppCompatActivity() {
    private lateinit var mScaleGestureDetector: ScaleGestureDetector
    private var mScaleFactor = 1.0f
    private lateinit var binding: ActivityFullScreenImageBinding // Replace with your generated binding class
    private lateinit var fullScreenImageView: ImageView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fullScreenImageView = binding.fullScreenImageView
        mScaleGestureDetector = ScaleGestureDetector(this, ScaleListener())
        val imageUrl = intent.getStringExtra(Constants.IMAGE_URl)

        // Load the image into the ImageView using Glide
        Glide.with(this)
            .load(imageUrl)
            .fitCenter()
            .into(fullScreenImageView)
        supportActionBar?.title = Constants.IMAGE_VIEW
        // Hide the status bar and action bar for full-screen display
        window.decorView.apply {
            systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    )
        }

        // Enable back navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        mScaleGestureDetector.onTouchEvent(motionEvent)
        return true
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = max(0.1f, min(mScaleFactor, 10.0f))
            fullScreenImageView.scaleX = mScaleFactor
            fullScreenImageView.scaleY = mScaleFactor
            return true
        }
    }
}

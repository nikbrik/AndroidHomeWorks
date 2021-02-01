package com.nikbrik.viewandlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.nikbrik.viewandlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        Glide.with(this)
            .load("https://images.freeimages.com/images/large-previews/670/snowman-1526046.jpg")
            .into(binding.helloImage)
    }
}

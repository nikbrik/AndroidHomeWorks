package com.nikbrik.viewandlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.nikbrik.viewandlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        Glide.with(binding.helloImage.context)
            .load("https://images.freeimages.com/images/large-previews/670/snowman-1526046.jpg")
            .into(binding.helloImage)
        setContentView(view)
    }
}
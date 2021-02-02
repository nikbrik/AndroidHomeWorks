package com.nikbrik.viewandlayout

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.nikbrik.viewandlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
//        binding.imageUrl = "https://images.freeimages.com/images/large-previews/5c6/sunset-jungle-1383333.jpg"
//        val imageView = findViewById<ImageView>(R.id.hello_image)
//        val imageUrl =
////            "https://images.freeimages.com/images/large-previews/670/snowman-1526046.jpg"
//            "https://images.freeimages.com/images/large-previews/5c6/sunset-jungle-1383333.jpg"
//
//        Glide.with(binding.helloImage.context)
//            .load(binding.imageUrl)
//            .into(binding.helloImage)
    }
}

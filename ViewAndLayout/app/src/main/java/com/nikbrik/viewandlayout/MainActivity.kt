package com.nikbrik.viewandlayout

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.nikbrik.viewandlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val binding = ActivityMainBinding.inflate(layoutInflater)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.imageUrl = "https://images.freeimages.com/images/large-previews/670/snowman-1526046.jpg"
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

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri =
            imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
//            .apply(RequestOptions()
//                .placeholder(R.drawable.loading_animation)
//                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}
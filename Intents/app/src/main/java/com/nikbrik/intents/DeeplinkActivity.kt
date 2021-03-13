package com.nikbrik.intents

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nikbrik.intents.databinding.ActivityDeeplinkBinding

class DeeplinkActivity: AppCompatActivity(R.layout.activity_deeplink) {
    private lateinit var binding: ActivityDeeplinkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDeeplinkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.data?.let {
            binding.deeplinkText.text = it.toString()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent?.data?.let {
            binding.deeplinkText.text = "OnNewIntent: ${it}"
        }

    }
}
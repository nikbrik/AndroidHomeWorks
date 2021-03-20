package com.nikbrik.fragments

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.fragments.databinding.ActivityDeeplinkBinding

class DeeplinkActivity : AppCompatActivity(R.layout.activity_deeplink) {

    private val binding: ActivityDeeplinkBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deeplink)

        intent.data?.let {
            binding.deeplinkText.text = it.toString()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent?.data?.let {
            binding.deeplinkText.text = "OnNewIntent: $it"
        }
    }
}

package com.nikbrik.networking.app

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.nikbrik.networking.R
import com.nikbrik.networking.mvvm.NetworkingViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<NetworkingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

package com.nikbrik.moshiHW.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.nikbrik.moshiHW.R

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MoshiHwViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

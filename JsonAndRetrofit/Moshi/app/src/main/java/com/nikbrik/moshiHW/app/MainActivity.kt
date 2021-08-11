package com.nikbrik.moshiHW.app

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.nikbrik.moshiHW.R
import com.nikbrik.moshiHW.mvvm.MoshiHwViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MoshiHwViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

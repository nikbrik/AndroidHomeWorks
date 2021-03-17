package com.nikbrik.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.fragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.findFragmentById(R.id.fragment_container) ?: run {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, CallFragment())
                .commit()
        }
    }
}
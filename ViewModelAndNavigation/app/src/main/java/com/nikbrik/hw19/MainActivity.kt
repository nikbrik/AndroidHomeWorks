package com.nikbrik.hw19

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.BuildConfig
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (BuildConfig.DEBUG && Timber.treeCount() == 0) Timber.plant(Timber.DebugTree())

        supportFragmentManager.run {
            findFragmentById(R.id.container) ?: beginTransaction()
                .add(R.id.container, MainFragment())
                .commit()
        }
    }
}

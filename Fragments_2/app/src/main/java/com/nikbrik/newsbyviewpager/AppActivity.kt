package com.nikbrik.newsbyviewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.BuildConfig
import timber.log.Timber

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        if (BuildConfig.DEBUG && Timber.treeCount() == 0) Timber.plant(Timber.DebugTree())

        supportFragmentManager.apply {
            findFragmentById(R.id.container) ?: beginTransaction()
                .add(R.id.container, ViewPagerFragment())
                .commit()
        }
    }
}

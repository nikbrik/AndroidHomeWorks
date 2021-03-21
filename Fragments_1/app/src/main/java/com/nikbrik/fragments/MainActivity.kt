package com.nikbrik.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    lateinit var childOnBackPressedAction: () -> Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Timber.treeCount() == 0) Timber.plant(Timber.DebugTree())
        Timber.d("nikbrik: onCreate")

        supportFragmentManager.findFragmentById(R.id.main_activity_container) ?: run {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_activity_container, MainFragment())
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        Timber.d("nikbrik: onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.d("nikbrik: onRestart")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("nikbrik: onResume")
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        Timber.d("nikbrik: onResumeFragments")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("nikbrik: onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("nikbrik: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("nikbrik: onDestroy")
    }

    override fun onBackPressed() {
        if (this::childOnBackPressedAction.isInitialized) {
            if (run(childOnBackPressedAction)) {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }
}

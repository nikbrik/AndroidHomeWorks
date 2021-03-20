package com.nikbrik.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    lateinit var childOnBackPressedAction: () -> Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.findFragmentById(R.id.main_activity_container) ?: run {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_activity_container, MainFragment())
                .commit()
        }
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

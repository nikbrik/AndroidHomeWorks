package com.nikbrik.intents

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseAuthActivity : AppCompatActivity() {

    companion object {
        var isAuthorized = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isAuthorized.not()) {
            val loginIntent =
                Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }
    }
}
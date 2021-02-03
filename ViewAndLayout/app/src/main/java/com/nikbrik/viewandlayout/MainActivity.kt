package com.nikbrik.viewandlayout

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.nikbrik.viewandlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).run {
            val view = root
            Glide.with(helloImage.context)
                .load("https://www.freeimages.com/download/file/dfc4fb4259559aded8784be143f3513e/640x480")
                .into(helloImage)
            setContentView(view)

            email.addTextChangedListener {
                updateLoginButton(
                    loginButton,
                    email,
                    password,
                    agree
                )
            }
            password.addTextChangedListener {
                updateLoginButton(
                    loginButton,
                    email,
                    password,
                    agree
                )
            }
            agree.setOnClickListener {
                updateLoginButton(
                    loginButton,
                    email,
                    password,
                    agree
                )
            }
        }
    }
}

inline fun updateLoginButton(button: Button, email: EditText, password: EditText, agree: CheckBox) {
    button.visibility =
        if (!email.text.isBlank() && !password.text.isBlank() && agree.isChecked) View.VISIBLE
        else View.INVISIBLE
}

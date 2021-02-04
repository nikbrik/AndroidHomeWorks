package com.nikbrik.viewandlayout

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.nikbrik.viewandlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        updateLoginButton(
            findViewById(R.id.login_button),
            findViewById(R.id.email),
            findViewById(R.id.password),
            findViewById(R.id.agree)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).run {
            val view = root
            Glide.with(helloImage.context)
                .load(getString(R.string.hello_image_src))
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
            loginButton.setOnClickListener { loginButton ->
                ProgressBar(loginButton.context, null, android.R.attr.progressBarStyleLarge).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply { gravity = Gravity.CENTER }
                }.let {
                    loginButton.isEnabled = false
                    email.isEnabled = false
                    password.isEnabled = false
                    agree.isEnabled = false
                    container.apply {
                        addView(it)
                        Handler(Looper.getMainLooper()).postDelayed(
                            {
                                removeView(it)
                                loginButton.isEnabled = true
                                email.isEnabled = true
                                password.isEnabled = true
                                agree.isEnabled = true
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.login_success_string),
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            2000
                        )
                    }
                }
            }
        }
    }
}

inline fun updateLoginButton(button: Button, email: EditText, password: EditText, agree: CheckBox) {
    button.isEnabled = (email.text.isNotBlank() && password.text.isNotBlank() && agree.isChecked)
}

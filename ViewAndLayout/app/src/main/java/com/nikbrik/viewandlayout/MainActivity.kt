package com.nikbrik.viewandlayout

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
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
                }
//                loginButton.context.contaiter.
            }
        }
    }
}

inline fun updateLoginButton(button: Button, email: EditText, password: EditText, agree: CheckBox) {
    button.visibility =
        if (!email.text.isBlank() && !password.text.isBlank() && agree.isChecked) View.VISIBLE
        else View.INVISIBLE
}

package com.nikbrik.viewandlayout

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.nikbrik.viewandlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onResume() {
        super.onResume()

        // Корректная доступность кнопки при повороте экрана
        updateLoginButton()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).run {
            activityMainBinding = this
            Glide.with(helloImage.context)
                .load(getString(R.string.hello_image_src))
                .into(helloImage)
            setContentView(root)

            email.addTextChangedListener {
                updateLoginButton()
            }
            password.addTextChangedListener {
                updateLoginButton()
            }
            agree.setOnClickListener {
                updateLoginButton()
            }
            loginButton.setOnClickListener { loginButton ->
                // Создание нового прогресс бара при нажатии кнопки
                ProgressBar(loginButton.context, null, android.R.attr.progressBarStyleLarge).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply { gravity = Gravity.CENTER }
                }.let {
                    // При ландшафтной ориентации progress bar не влезает на экран,
                    // уходит вниз скроллвью и неочевидно что что-то происходит,
                    // поэтому принял решение выводить его на место кнопки
                    loginButton.visibility = LinearLayout.INVISIBLE
                    email.isEnabled = false
                    password.isEnabled = false
                    agree.isEnabled = false
                    container.apply {
                        addView(it, indexOfChild(loginButton))
                        Handler(Looper.getMainLooper()).postDelayed(
                            {
                                // Возвращение состояния UI назад через 2 сек.
                                removeView(it)
                                loginButton.visibility = LinearLayout.VISIBLE
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

    private fun updateLoginButton() {
        activityMainBinding.run {
            loginButton.isEnabled =
                (email.text.isNotBlank() && password.text.isNotBlank() && agree.isChecked)
        }
    }
}

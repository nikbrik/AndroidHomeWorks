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

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        Glide.with(activityMainBinding.helloImage.context)
            .load(getString(R.string.hello_image_src))
            .into(activityMainBinding.helloImage)
        setContentView(activityMainBinding.root)

        activityMainBinding.email.addTextChangedListener {
            updateLoginButton()
        }
        activityMainBinding.password.addTextChangedListener {
            updateLoginButton()
        }
        activityMainBinding.agree.setOnClickListener {
            updateLoginButton()
        }
        activityMainBinding.loginButton.setOnClickListener { button ->
            // Создание нового прогресс бара при нажатии кнопки
            val newProgressBar =
                ProgressBar(this, null, android.R.attr.progressBarStyleLarge)
            newProgressBar.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { gravity = Gravity.CENTER }

            // При ландшафтной ориентации progress bar не влезает на экран,
            // уходит вниз скроллвью и неочевидно что что-то происходит,
            // поэтому принял решение выводить его на место кнопки
            setViewsState(false)
            activityMainBinding.container.apply {
                addView(newProgressBar, indexOfChild(button))
            }
            // Возвращение состояния UI назад через 2 сек.
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    setViewsState(true)
                    activityMainBinding.container.removeView(newProgressBar)
                    showTextInMainActivity(R.string.login_success_string)
                },
                2000
            )
        }
    }

    private fun showTextInMainActivity(textId: Int) {
        Toast.makeText(
            this,
            this.getString(textId),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setViewsState(isEnabled: Boolean) {
        activityMainBinding.apply {
            loginButton.visibility = if (isEnabled) LinearLayout.VISIBLE else LinearLayout.INVISIBLE
            email.isEnabled = isEnabled
            password.isEnabled = isEnabled
            agree.isEnabled = isEnabled
        }
    }

    private fun updateLoginButton() {
        activityMainBinding.apply {
            loginButton.isEnabled =
                (email.text.isNotBlank() && password.text.isNotBlank() && agree.isChecked)
        }
    }
}

package com.nikbrik.activitylifecircle

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.nikbrik.activitylifecircle.databinding.ActivityMainBinding
import timber.log.Timber
import timber.log.Timber.DebugTree

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        Timber.v("onCreate")

        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        Glide.with(mainBinding.helloImage.context)
            .load(getString(R.string.hello_image_src))
            .into(mainBinding.helloImage)
        setContentView(mainBinding.root)

        mainBinding.email.addTextChangedListener {
            updateLoginButton()
        }
        mainBinding.password.addTextChangedListener {
            updateLoginButton()
        }
        mainBinding.agree.setOnClickListener {
            updateLoginButton()
        }
        mainBinding.loginButton.setOnClickListener { button ->
            // Создание нового прогресс бара при нажатии кнопки
            val newProgressBar =
                ProgressBar(this, null, android.R.attr.progressBarStyleLarge)
            newProgressBar.layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                horizontalBias = 0.5f
                topToBottom = R.id.password
            }

            // При ландшафтной ориентации progress bar не влезает на экран,
            // уходит вниз скроллвью и неочевидно что что-то происходит,
            // поэтому принял решение выводить его на место кнопки
            setViewsState(false)
            mainBinding.container.apply {
                addView(newProgressBar, indexOfChild(button))
            }
            // Возвращение состояния UI назад через 2 сек.
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    setViewsState(true)
                    mainBinding.container.removeView(newProgressBar)
                    showTextInMainActivity(R.string.login_success_string)
                },
                2000
            )
        }
    }

    override fun onStart() {
        super.onStart()
        Timber.d("onStart")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume")

        // Корректная доступность кнопки при повороте экрана
        updateLoginButton()
    }

    override fun onPause() {
        super.onPause()
        Timber.e("onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.w("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.wtf("onDestroy")
    }

    private fun showTextInMainActivity(textId: Int) {
        Toast.makeText(
            this,
            this.getString(textId),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setViewsState(isEnabled: Boolean) {
        mainBinding.apply {
//            loginButton.visibility = if (isEnabled) LinearLayout.VISIBLE else LinearLayout.INVISIBLE
            interactiveGroup.visibility =
                if (isEnabled) LinearLayout.VISIBLE else LinearLayout.INVISIBLE
            email.isEnabled = isEnabled
            password.isEnabled = isEnabled
            agree.isEnabled = isEnabled
        }
    }

    private fun updateLoginButton() {
        mainBinding.apply {
            loginButton.isEnabled =
                (email.text.isNotBlank() && password.text.isNotBlank() && agree.isChecked)
        }
    }
}

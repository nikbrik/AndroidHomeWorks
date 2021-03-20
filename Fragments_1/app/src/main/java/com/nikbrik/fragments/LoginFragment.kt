package com.nikbrik.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Patterns
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.nikbrik.fragments.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val KEY_STATE = "FORM_STATE"
    private val binding: FragmentLoginBinding by viewBinding()
    private var state = FormState(false, "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(binding.helloImage.context)
            .load(getString(R.string.hello_image_src))
            .into(binding.helloImage)

        binding.email.addTextChangedListener {
            updateLoginButton()
        }
        binding.password.addTextChangedListener {
            updateLoginButton()
        }
        binding.agree.setOnClickListener {
            updateLoginButton()
        }
        binding.loginButton.setOnClickListener { button ->
            // Создание нового прогресс бара при нажатии кнопки
            val newProgressBar =
                ProgressBar(this.context, null, android.R.attr.progressBarStyleLarge)
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
            binding.container.apply {
                addView(newProgressBar, indexOfChild(button))
            }
            // Возвращение состояния UI назад через 2 сек.
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    setViewsState(true)
                    binding.container.removeView(newProgressBar)
                    validateLoginInformation()
                },
                1000
            )
        }
        binding.anrButton.setOnClickListener { Thread.sleep(6000) }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            state = savedInstanceState.getParcelable(KEY_STATE) ?: FormState(false, "")
            binding.loginButton.isEnabled = state.loginButtonAvailable
            binding.validation.text = state.validationText
        }
    }

    private fun validateLoginInformation() {

        // validation here TODO

        BaseAuthFragment.isAuthorized = true

        parentFragmentManager.beginTransaction()
            .replace(R.id.main_activity_container, MainFragment())
            .commit()
    }

    private fun toast(textId: Int) {
        Toast.makeText(
            this.context,
            this.getString(textId),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setViewsState(isEnabled: Boolean) {
        binding.apply {
            interactiveGroup.visibility =
                if (isEnabled) LinearLayout.VISIBLE else LinearLayout.INVISIBLE
            email.isEnabled = isEnabled
            password.isEnabled = isEnabled
            agree.isEnabled = isEnabled
        }
    }

    private fun updateLoginButton() {
        binding.apply {
            if (Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
                loginButton.isEnabled =
                    (email.text.isNotBlank() && password.text.isNotBlank() && agree.isChecked)
            } else {
                toast(R.string.invalid_email)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        state.loginButtonAvailable = binding.loginButton.isEnabled
        state.validationText = binding.validation.text.toString()
        outState.putParcelable(KEY_STATE, state)
    }
}

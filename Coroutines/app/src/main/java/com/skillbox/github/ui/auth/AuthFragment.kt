package com.skillbox.github.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.github.R
import com.skillbox.github.databinding.FragmentAuthBinding
import com.skillbox.github.utils.toast
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private val binding: FragmentAuthBinding by viewBinding()

    private val viewModel: AuthViewModel by viewModels()
    private val openAuthPageContract =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            result?.data?.let { openAuthPageCallback(it) }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
    }

    private fun openAuthPageCallback(data: Intent) {
        val tokenExchangeRequest = AuthorizationResponse.fromIntent(data)
            ?.createTokenExchangeRequest()
        val exception = AuthorizationException.fromIntent(data)
        when {
            tokenExchangeRequest != null && exception == null ->
                viewModel.onAuthCodeReceived(tokenExchangeRequest)
            exception != null -> viewModel.onAuthCodeFailed(exception)
        }
    }

    private fun bindViewModel() {
        binding.loginButton.setOnClickListener { viewModel.openLoginPage() }
        viewModel.loadingLiveData.observe(viewLifecycleOwner, ::updateIsLoading)
        viewModel.openAuthPageLiveData.observe(viewLifecycleOwner, ::openAuthPage)
        viewModel.toastLiveData.observe(viewLifecycleOwner, ::toast)
        viewModel.authSuccessLiveData.observe(viewLifecycleOwner) {
            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToMainFragment())
        }
    }

    private fun updateIsLoading(isLoading: Boolean) {
        binding.loginButton.isVisible = !isLoading
        binding.loginProgress.isVisible = isLoading
    }

    private fun openAuthPage(intent: Intent) {
        openAuthPageContract.launch(intent)
    }
}

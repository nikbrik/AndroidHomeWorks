package com.skillbox.CoroutinesApp.ui.current_user

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.skillbox.CoroutinesApp.R
import com.skillbox.CoroutinesApp.databinding.FragmentUserBinding

class CurrentUserFragment : Fragment(R.layout.fragment_user) {
    private val binding: FragmentUserBinding by viewBinding()
    private val viewModel: CurrentUserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeCurrentUserInfo()

        viewModel.getCurrentUserInfo()
    }

    private fun observeCurrentUserInfo() {
        viewModel.user.observe(
            viewLifecycleOwner,
            { t ->
                if (t != null) {
                    binding.avatar.load(t.avatar_url) {
                        placeholder(R.drawable.ic_launcher_background)
                        error(R.drawable.ic_launcher_foreground)
                    }
                    binding.login.text = t.login
                    binding.type.text = t.type
                    val link = t.html_url
                    binding.profile.movementMethod = LinkMovementMethod.getInstance()
                    binding.profile.text = HtmlCompat.fromHtml(
                        "<a href='$link'> $link </a>",
                        HtmlCompat.FROM_HTML_MODE_COMPACT
                    )
                }
            }
        )
    }
}

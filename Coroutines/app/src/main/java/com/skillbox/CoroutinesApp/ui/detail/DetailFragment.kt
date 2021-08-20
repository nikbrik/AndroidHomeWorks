package com.skillbox.CoroutinesApp.ui.detail

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.CoroutinesApp.R
import com.skillbox.CoroutinesApp.databinding.FragmentDetailBinding
import com.skillbox.CoroutinesApp.network.Repository

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val args: DetailFragmentArgs by navArgs()
    private val binding: FragmentDetailBinding by viewBinding()
    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.repository.let {

            setStarState(false)

            viewModel.isStarred(it)

            bindRepoInfo(it)
        }

        observeStarInfo()

        binding.starOn.setOnClickListener {
            viewModel.setStar(args.repository, false)
        }
        binding.starOff.setOnClickListener {
            viewModel.setStar(args.repository, true)
        }
    }

    private fun observeStarInfo() {
        viewModel.starredEvent.observe(
            viewLifecycleOwner,
            { t -> setStarState(t ?: false) }
        )
    }

    private fun bindRepoInfo(it: Repository) {
        binding.fullName.text = it.full_name
        binding.privateFlag.isVisible = it.private
        binding.description.text = it.description

        val link = it.html_url
        binding.htmlUrl.movementMethod = LinkMovementMethod.getInstance()
        binding.htmlUrl.text = HtmlCompat.fromHtml(
            "<a href='$link'> $link </a>",
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
    }

    private fun setStarState(state: Boolean) {
        binding.starOn.isVisible = state
        binding.starOff.isVisible = !state
    }
}

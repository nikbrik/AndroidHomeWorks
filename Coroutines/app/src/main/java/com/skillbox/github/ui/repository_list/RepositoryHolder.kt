package com.skillbox.github.ui.repository_list

import android.text.method.LinkMovementMethod
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.github.databinding.FragmentRepositoryBinding
import com.skillbox.github.network.Repository

class RepositoryHolder(private val binding: FragmentRepositoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(repository: Repository) {
        binding.fullName.text = repository.full_name
        binding.privateFlag.isVisible = repository.private
        binding.description.text = repository.description

        val link = repository.html_url
        binding.htmlUrl.movementMethod = LinkMovementMethod.getInstance()
        binding.htmlUrl.text = HtmlCompat.fromHtml(
            "<a href='$link'> $link </a>",
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        binding.root.setOnClickListener { rootView ->
            val action =
                RepositoryListFragmentDirections.actionRepositoryListFragmentToDetailFragment(
                    repository
                )
            rootView.findNavController().navigate(action)
        }
    }
}

package com.skillbox.CoroutinesApp.ui.repository_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.CoroutinesApp.R
import com.skillbox.CoroutinesApp.databinding.FragmentListRepositoryBinding

class RepositoryListFragment : Fragment(R.layout.fragment_list_repository) {
    private val binding: FragmentListRepositoryBinding by viewBinding()
    private val viewModel: ListRepositoryViewModel by viewModels()
    private val repositoryAdapter = RepositoryAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
    }

    private fun initList() {
        binding.list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = repositoryAdapter
        }
        viewModel.repositories.observe(
            viewLifecycleOwner,
            { t -> repositoryAdapter.items = t ?: emptyList() }
        )
        viewModel.getRepositories()
    }
}

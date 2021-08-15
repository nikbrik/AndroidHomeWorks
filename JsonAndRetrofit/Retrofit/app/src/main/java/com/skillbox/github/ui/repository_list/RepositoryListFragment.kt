package com.skillbox.github.ui.repository_list

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.github.R
import com.skillbox.github.databinding.FragmentListRepositoryBinding

class RepositoryListFragment : Fragment(R.layout.fragment_list_repository) {
    private val binding: FragmentListRepositoryBinding by viewBinding()
    private val viewModel: ListRepositoryViewModel by viewModels()
}

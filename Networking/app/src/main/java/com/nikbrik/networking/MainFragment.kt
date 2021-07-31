package com.nikbrik.networking

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.networking.databinding.FragmentMainBinding

class MainFragment() : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding()
//    private val viewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
    }

    private fun initList() {
        binding.list.apply {
            adapter = MovieAdapter()
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }
}

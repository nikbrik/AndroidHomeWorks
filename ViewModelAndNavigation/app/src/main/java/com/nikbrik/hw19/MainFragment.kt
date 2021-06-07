package com.nikbrik.hw19

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.hw19.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.linearListButton.setOnClickListener {
            openListFragment(TYPE_LINEAR)
        }
        binding.gridListButton.setOnClickListener {
            openListFragment(TYPE_GRID)
        }
        binding.staggeredGridListButton.setOnClickListener {
            openListFragment(TYPE_STAGGERED_GRID)
        }
    }

    private fun openListFragment(type: Int) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToListFragment(type))
    }
}

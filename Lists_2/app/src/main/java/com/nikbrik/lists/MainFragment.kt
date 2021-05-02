package com.nikbrik.lists

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.lists.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.linearListButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment().withArguments {
                    putInt(ListFragment.KEY_LAYOUT_MANAGER_TYPE, ListFragment.TYPE_LINEAR)
                })
                .addToBackStack(null)
                .commit()
        }
        binding.gridListButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment().withArguments {
                    putInt(ListFragment.KEY_LAYOUT_MANAGER_TYPE, ListFragment.TYPE_GRID)
                })
                .addToBackStack(null)
                .commit()
        }
        binding.staggeredGridListButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment().withArguments {
                    putInt(ListFragment.KEY_LAYOUT_MANAGER_TYPE, ListFragment.TYPE_STAGGERED_GRID)
                })
                .addToBackStack(null)
                .commit()
        }
    }
}
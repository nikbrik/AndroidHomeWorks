package com.nikbrik.hw19

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.hw19.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.linearListButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    ListFragment().withArguments {
                        putInt(ListFragment.KEY_LAYOUT_MANAGER_TYPE, TYPE_LINEAR)
                    }
                )
                .addToBackStack(null)
                .commit()
        }
        binding.gridListButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    ListFragment().withArguments {
                        putInt(ListFragment.KEY_LAYOUT_MANAGER_TYPE, TYPE_GRID)
                    }
                )
                .addToBackStack(null)
                .commit()
        }
        binding.staggeredGridListButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    ListFragment().withArguments {
                        putInt(ListFragment.KEY_LAYOUT_MANAGER_TYPE, TYPE_STAGGERED_GRID)
                    }
                )
                .addToBackStack(null)
                .commit()
        }
    }
}

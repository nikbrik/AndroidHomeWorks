package com.nikbrik.filesapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.filesapp.R
import com.nikbrik.filesapp.databinding.FragmentMainBinding

const val SHARED_PREFERENCE_SETTINGS = 0
const val DATA_STORE_SETTINGS = 1

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sharedPreference.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToFileFragment(
                    SHARED_PREFERENCE_SETTINGS
                )
            )
        }
        binding.dataStore.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToFileFragment(
                    DATA_STORE_SETTINGS
                )
            )
        }
    }
}

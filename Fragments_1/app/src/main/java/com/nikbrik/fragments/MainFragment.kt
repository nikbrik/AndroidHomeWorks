package com.nikbrik.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager

class MainFragment : BaseAuthFragment(R.layout.fragment_main) {

    private var addedToBackStack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as? MainActivity)?.childOnBackPressedAction = ::showListFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showListFragment()
    }

    private fun showListFragment(): Boolean {
        val entryCountZero = childFragmentManager.backStackEntryCount == 0
        if (isAuthorized) {
            if (addedToBackStack) {
                childFragmentManager.popBackStack()
            } else {
                addedToBackStack = true
                childFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment_container, ListFragment())
                    .commit()
            }
        }
        return entryCountZero
    }
}

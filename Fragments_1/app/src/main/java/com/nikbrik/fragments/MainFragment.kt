package com.nikbrik.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.View

class MainFragment : BaseAuthFragment(R.layout.fragment_main) {

    private var listAddedToBackStack = false
    private var needShowList = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as? MainActivity)?.childOnBackPressedAction = ::showListFragment
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            (activity as? MainActivity)?.childOnBackPressedAction = ::showListFragment
            needShowList = true
        } else {
            (activity as? MainActivity)?.childOnBackPressedAction = { true }
            needShowList = false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showListFragment()
    }

    private fun showListFragment(): Boolean {
        val entryCountZero = childFragmentManager.backStackEntryCount == 0
        if (isAuthorized && needShowList) {
            if (listAddedToBackStack) {
                childFragmentManager.popBackStack()
            } else {
                listAddedToBackStack = true
                childFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment_container, ListFragment())
                    .commit()
            }
        }
        return entryCountZero
    }
}

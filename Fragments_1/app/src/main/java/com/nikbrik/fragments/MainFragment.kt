package com.nikbrik.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import timber.log.Timber

class MainFragment : BaseAuthFragment(R.layout.fragment_main) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d("${this.hashCode()} nikbrik: onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("${this.hashCode()} nikbrik: onCreate")

        (activity as? MainActivity)?.childOnBackPressedAction = ::isBackActionEnable

        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT ->
                childFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment_container, ListFragment())
                    .commit()
            Configuration.ORIENTATION_LANDSCAPE -> {
                val listFragment =
                    childFragmentManager.findFragmentById(R.id.main_fragment_container)
                if (listFragment != null)
                    childFragmentManager.beginTransaction()
                        .remove(listFragment)
                        .commit()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("${this.hashCode()} nikbrik: onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("${this.hashCode()} nikbrik: onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Timber.d("${this.hashCode()} nikbrik: onStart")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("${this.hashCode()} nikbrik: onResume")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("${this.hashCode()} nikbrik: onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("${this.hashCode()} nikbrik: onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("${this.hashCode()} nikbrik: onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("${this.hashCode()} nikbrik: onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.d("${this.hashCode()} nikbrik: onDetach")
    }

    private fun isBackActionEnable(): Boolean {
        return if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) true
        else {
            val entryCountZero = childFragmentManager.backStackEntryCount == 0
            childFragmentManager.popBackStack()
            entryCountZero
        }
    }
}

package com.nikbrik.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.fragments.databinding.FragmentListBinding
import timber.log.Timber

class ListFragment : Fragment(R.layout.fragment_list) {

    private val binding: FragmentListBinding by viewBinding()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d("${this.hashCode()} nikbrik: onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("${this.hashCode()} nikbrik: onCreate")
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

        var index = 1
        (binding.list as ViewGroup)
            .children
            .mapNotNull { it as? TextView }
            .forEach { listItem ->
                listItem.text = "List item $index"
                listItem.setOnClickListener { thisView ->
                    showDetailInformation((thisView as TextView).text.toString())
                }
                index++
            }
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

    private fun showDetailInformation(text: String) {
        parentFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            replace(R.id.main_fragment_container, DetailFragment.newInstance(text))
            addToBackStack(null)
        }
    }
}

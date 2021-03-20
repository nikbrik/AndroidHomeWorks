package com.nikbrik.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.fragments.databinding.FragmentListBinding

class ListFragment : Fragment(R.layout.fragment_list) {

    private val binding: FragmentListBinding by viewBinding()

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

    private fun showDetailInformation(text: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, DetailFragment.newInstance(text))
            .addToBackStack(null)
            .commit()
    }
}

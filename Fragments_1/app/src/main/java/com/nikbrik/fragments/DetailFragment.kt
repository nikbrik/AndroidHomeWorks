package com.nikbrik.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nikbrik.fragments.databinding.FragmentDetailBinding

private const val ARG_DETAIL_TEXT = "detailText"

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding: FragmentDetailBinding by viewBinding()
    private lateinit var detailText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            detailText = it.getString(ARG_DETAIL_TEXT) ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailText.text = detailText
    }

    companion object {
        fun newInstance(detailText: String) =
            DetailFragment().withArguments {
                putString(ARG_DETAIL_TEXT, detailText)
            }
    }
}

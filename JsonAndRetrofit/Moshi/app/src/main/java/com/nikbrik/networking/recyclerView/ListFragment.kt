package com.nikbrik.networking.recyclerView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikbrik.networking.R
import com.nikbrik.networking.databinding.FragmentListBinding
import com.nikbrik.networking.extensions.autoCleared
import com.nikbrik.networking.mvvm.NetworkingViewModel

class ListFragment : Fragment(R.layout.fragment_list) {
    private var binding: FragmentListBinding? = null
    private val viewModel: NetworkingViewModel by activityViewModels()
    private var movieAdapter: MovieAdapter by autoCleared()
    private val args: ListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBackPressedAction()

        initList()

        binding?.repeat?.setOnClickListener {
            backToSearchFragment(true)
        }
    }

    private fun setBackPressedAction() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    backToSearchFragment(false)
                }
            }
        )
    }

    private fun backToSearchFragment(repeat: Boolean) {
        val actionDetail =
            ListFragmentDirections.actionListFragmentToSearchFragment()
        actionDetail.repeat = repeat
        findNavController().navigate(actionDetail)
    }

    private fun initList() {
        movieAdapter = MovieAdapter()
        binding?.apply {
            list.apply {
                adapter = movieAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
        observeMovieListUpdate()
    }

    private fun observeMovieListUpdate() {
        viewModel.movies.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                setErrorInterface(true, args.errorMessage)
            } else {
                movieAdapter.items = it
            }
        }
    }

    private fun setErrorInterface(isVisible: Boolean, message: String) {
        binding?.apply {
            error.isVisible = isVisible
            repeat.isVisible = isVisible
            list.isVisible = isVisible.not()
            error.text = message
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}

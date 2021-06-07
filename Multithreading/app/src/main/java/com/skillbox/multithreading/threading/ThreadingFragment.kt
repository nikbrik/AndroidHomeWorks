package com.skillbox.multithreading.threading

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.multithreading.R
import com.skillbox.multithreading.adapters.MovieAdapter
import com.skillbox.multithreading.autoCleared
import com.skillbox.multithreading.databinding.FragmentThreadingBinding
import com.skillbox.multithreading.networking.Movie

class ThreadingFragment : Fragment(R.layout.fragment_threading) {
    private val binding: FragmentThreadingBinding by viewBinding()
    private var movieAdapter: MovieAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
    }

    private fun initList() {
        movieAdapter =
            MovieAdapter(
                { position ->
//                    findNavController().navigate(
//                        ListFragmentDirections.actionListFragmentToDetailFragment(position)
//                    )
                },
            )
        binding.recyclerView.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())

            setHasFixedSize(true)

//            layoutManager?.let {
//                addOnScrollListener(
//                    object : EndlessRecyclerViewScrollListener(it) {
//                        override fun onLoadMore(
//                            page: Int,
//                            totalItemsCount: Int,
//                            view: RecyclerView?
//                        ) {
//                            productsViewModel.loadMore(totalItemsCount)
//                        }
//                    }
//                )
//            }
        }
        movieAdapter.items = listOf(
            Movie(title = "New film", year = 2021, image = ""),
            Movie(title = "New film", year = 2021, image = ""),
            Movie(title = "New film", year = 2021, image = ""),
            Movie(title = "New film", year = 2021, image = ""),
            Movie(title = "New film", year = 2021, image = ""),
            Movie(title = "New film", year = 2021, image = ""),
            Movie(title = "New film", year = 2021, image = ""),
            Movie(title = "New film", year = 2021, image = ""),
            Movie(title = "New film", year = 2021, image = ""),
            Movie(title = "New film", year = 2021, image = ""),
            Movie(title = "New film", year = 2021, image = ""),
            Movie(title = "New film", year = 2021, image = ""),
        )
    }
}

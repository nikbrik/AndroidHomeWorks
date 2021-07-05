package com.skillbox.multithreading.threading

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.multithreading.R
import com.skillbox.multithreading.ThreadingViewModel
import com.skillbox.multithreading.adapters.MovieAdapter
import com.skillbox.multithreading.autoCleared
import com.skillbox.multithreading.databinding.FragmentThreadingBinding
import com.skillbox.multithreading.networking.Movie
import timber.log.Timber

class ThreadingFragment : Fragment(R.layout.fragment_threading) {
    private val binding: FragmentThreadingBinding by viewBinding()
    private var movieAdapter: MovieAdapter by autoCleared()
    private val threadingViewModel: ThreadingViewModel by viewModels()
    private val mainHandler = Handler(Looper.getMainLooper())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
    }

    private fun observeViewModelState() {
        threadingViewModel.movies.observe(viewLifecycleOwner) {
            movieAdapter.items = it
        }
    }

    private fun initList() {

        binding.swipeRefresh.setOnRefreshListener {
            threadingViewModel.loadMovies { movies ->
                loadMoviesCallback(movies)
                binding.swipeRefresh.isRefreshing = false
            }
        }

        movieAdapter =
            MovieAdapter({ })
        binding.recyclerView.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())

            setHasFixedSize(true)
        }
        observeViewModelState()
        threadingViewModel.loadMovies { movies ->
            loadMoviesCallback(movies)
        }
    }

    private fun loadMoviesCallback(movies: List<Movie>) {
        mainHandler.post {
            Timber.e("nikbrik - movieAdapter.items.size = ${movieAdapter.items.size}")
            movieAdapter.items = movies
            Timber.e("nikbrik - movieAdapter.items.size = ${movieAdapter.items.size}")
            Timber.e("nikbrik - movies.size = ${movies.size}")
        }
        mainHandler.postDelayed(
            {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.listRefreshed),
                    Toast.LENGTH_SHORT
                ).show()
            },
            1000
        )
    }
}

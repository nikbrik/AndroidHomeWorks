package com.skillbox.multithreading

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.multithreading.networking.Movie

class ThreadingViewModel : ViewModel() {

    private val moviesRepository = MoviesRepository()
    private val moviesLiveData = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = moviesLiveData

    fun loadMovies() {
        moviesRepository.fetchMovies {
            moviesLiveData.postValue(it)
        }
    }
}

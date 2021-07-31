package com.nikbrik.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NetworkingViewModel : ViewModel() {
    private val repository = NetworkingRepository()
    private val liveData = MutableLiveData<List<Movie>>(
//        repository.createNewList(context, 100)
    )
    val movies: LiveData<List<Movie>>
        get() = liveData
}

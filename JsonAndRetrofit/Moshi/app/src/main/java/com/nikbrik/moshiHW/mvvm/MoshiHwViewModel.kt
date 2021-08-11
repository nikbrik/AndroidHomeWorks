package com.nikbrik.moshiHW.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikbrik.moshiHW.recyclerView.ExtendedMovie

class MoshiHwViewModel : ViewModel() {

    var searchName = ""
    var searchYear = ""
    var searchType = ""
    var searchTypeArray = emptyArray<String>()

    private val repository = MoshiHwRepository()
    private val liveData = MutableLiveData<List<ExtendedMovie>>(
        emptyList()
    )
    val movies: LiveData<List<ExtendedMovie>>
        get() = liveData

    fun onSearchButtonClickEvent(
        name: String,
        year: String,
        uiType: String,
        callback: (errorMessage: String) -> Unit,
    ) {
        val type = mapUiValuesToApiParameters(uiType)
        repository.searchMovie(name, year, type) { movies, errorMessage ->
            liveData.postValue(movies)
            callback(errorMessage)
        }
    }

    private fun mapUiValuesToApiParameters(uiType: String): String {
        return when (searchTypeArray.indexOf(uiType)) {
            0 -> "movie"
            1 -> "series"
            2 -> "episode"
            else -> ""
        }
    }
}

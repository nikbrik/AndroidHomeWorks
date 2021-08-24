package com.skillbox.CoroutinesApp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.CoroutinesApp.data.GithubAppRepository
import com.skillbox.CoroutinesApp.network.Repository
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val githubAppRepository = GithubAppRepository()

    private val starredEventLiveData = MutableLiveData<Boolean>()
    val starredEvent: LiveData<Boolean>
        get() = starredEventLiveData

    fun isStarred(repository: Repository) {
        viewModelScope.launch {
            starredEventLiveData.postValue(
                githubAppRepository.repositoryIsStarred(
                    repository,
                )
            )
        }
    }

    fun setStar(repository: Repository, put: Boolean) {
        viewModelScope.launch {
            starredEventLiveData.postValue(
                githubAppRepository.setStar(
                    repository,
                    put,
                )
            )
        }
    }
}

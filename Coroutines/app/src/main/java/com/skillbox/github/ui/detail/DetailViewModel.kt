package com.skillbox.github.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.data.GithubAppRepository
import com.skillbox.github.network.Repository

class DetailViewModel : ViewModel() {
    private val githubAppRepository = GithubAppRepository()

    private val starredEventLiveData = MutableLiveData<Boolean>()
    val starredEvent: LiveData<Boolean>
        get() = starredEventLiveData

    fun isStarred(repository: Repository) {
        githubAppRepository.repositoryIsStarred(
            repository,
            onSuccess = { starredEventLiveData.postValue(it) },
            onError = { starredEventLiveData.postValue(false) },
        )
    }

    fun setStar(repository: Repository, put: Boolean) {
        githubAppRepository.setStar(
            repository,
            put,
            onSuccess = { starredEventLiveData.postValue(it) },
            onError = { }
        )
    }
}

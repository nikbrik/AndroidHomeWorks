package com.skillbox.github.ui.repository_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.data.GithubAppRepository
import com.skillbox.github.network.Repository

class ListRepositoryViewModel : ViewModel() {
    private val repository = GithubAppRepository()

    private val listRepositoryLiveData = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>>
        get() = listRepositoryLiveData

    fun getRepositories() {
        repository.getRepositoryList(
            onSuccess = { listRepositoryLiveData.postValue(it) },
            onError = { listRepositoryLiveData.postValue(emptyList()) },
        )
    }
}

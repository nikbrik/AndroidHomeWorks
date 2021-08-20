package com.skillbox.CoroutinesApp.ui.repository_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.CoroutinesApp.data.GithubAppRepository
import com.skillbox.CoroutinesApp.network.Repository

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

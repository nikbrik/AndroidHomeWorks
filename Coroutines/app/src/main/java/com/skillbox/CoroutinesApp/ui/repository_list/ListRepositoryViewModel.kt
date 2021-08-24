package com.skillbox.CoroutinesApp.ui.repository_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.CoroutinesApp.data.GithubAppRepository
import com.skillbox.CoroutinesApp.network.Repository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber

class ListRepositoryViewModel : ViewModel() {
    private val repository = GithubAppRepository()
    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        launchCoroutineAfterError()
    }
    private val uiScope = CoroutineScope(SupervisorJob() + Dispatchers.Main + errorHandler)

    private fun launchCoroutineAfterError() {
        uiScope.launch {
            Timber.d("uiScope work continue")
        }
    }

    private val listRepositoryLiveData = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>>
        get() = listRepositoryLiveData

    fun getRepositories() {
        uiScope.launch {
            listRepositoryLiveData.postValue(repository.getRepositoryList())
        }
    }

    override fun onCleared() {
        super.onCleared()

        uiScope.cancel()
    }
}

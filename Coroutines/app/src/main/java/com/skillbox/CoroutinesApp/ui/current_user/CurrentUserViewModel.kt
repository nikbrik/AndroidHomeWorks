package com.skillbox.CoroutinesApp.ui.current_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.CoroutinesApp.data.GithubAppRepository
import com.skillbox.CoroutinesApp.network.Follower
import com.skillbox.CoroutinesApp.network.User
import com.skillbox.CoroutinesApp.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CurrentUserViewModel : ViewModel() {

    private val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        errorLiveData.postValue(throwable.toString())
    }

    private val repository = GithubAppRepository()

    private val userLiveData = MutableLiveData<User?>()
    val user: LiveData<User?>
        get() = userLiveData

    private val followersLiveData = MutableLiveData<List<Follower>>()
    val followers: LiveData<List<Follower>>
        get() = followersLiveData

    private val errorLiveData = SingleLiveEvent<String>()
    val error: LiveData<String>
        get() = errorLiveData

    fun getCurrentUserInfo() {
        viewModelScope.launch(errorHandler) {
            val jobGetFollowers = async { repository.getFollowers() }
            val jobGetUser = async { repository.getUser() }
            userLiveData.postValue(jobGetUser.await())
            followersLiveData.postValue(jobGetFollowers.await())
        }
    }
}

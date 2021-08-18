package com.skillbox.github.ui.current_user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.data.GithubAppRepository
import com.skillbox.github.network.User

class CurrentUserViewModel : ViewModel() {

    private val repository = GithubAppRepository()

    private val userLiveData = MutableLiveData<User?>()
    val user: LiveData<User?>
        get() = userLiveData

    fun getCurrentUserInfo() {
        repository.getUser(
            onSuccess = { userLiveData.postValue(it) },
            onError = { userLiveData.postValue(null) },
        )
    }
}

package com.skillbox.github.data

import com.skillbox.github.network.Networking
import com.skillbox.github.network.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubAppRepository {
    fun getUser(
        onSuccess: (user: User?) -> Unit,
        onError: () -> Unit,
    ) {
        Networking.githubApi.user().enqueue(
            object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        onSuccess(response.body())
                    } else {
                        onError()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    onError()
                }
            }
        )
    }
}

package com.skillbox.github.data

import com.skillbox.github.network.Networking
import com.skillbox.github.network.Repository
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

    fun getRepositoryList(
        onSuccess: (repositoryList: List<Repository>?) -> Unit,
        onError: () -> Unit,
    ) {
        Networking.githubApi.repositories().enqueue(
            object : Callback<List<Repository>> {
                override fun onResponse(
                    call: Call<List<Repository>>,
                    response: Response<List<Repository>>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body())
                    } else {
                        onError()
                    }
                }

                override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                    onError()
                }
            }
        )
    }

    fun repositoryIsStarred(
        repository: Repository,
        onSuccess: (starred: Boolean) -> Unit,
        onError: () -> Unit,
    ) {
        Networking.githubApi.isStarred(
            repository.owner.login,
            repository.name
        )
            .enqueue(
                object : Callback<Unit> {
                    override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                        if (response.isSuccessful) {
                            val code = response.code()
                            onSuccess(code == 204)
                        } else {
                            onError()
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        onError()
                    }
                }
            )
    }

    fun setStar(
        repository: Repository,
        put: Boolean,
        onSuccess: (starred: Boolean) -> Unit,
        onError: () -> Unit,
    ) {
        if (put) {
            putStar(repository, onSuccess, onError)
        } else {
            deleteStar(repository, onSuccess, onError)
        }
    }

    private fun deleteStar(
        repository: Repository,
        onSuccess: (starred: Boolean) -> Unit,
        onError: () -> Unit
    ) {
        Networking.githubApi.deleteStar(
            repository.owner.login,
            repository.name
        ).enqueue(
            object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    val code = response.code()
                    onSuccess(code != 204)
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    onError()
                }
            }
        )
    }

    private fun putStar(
        repository: Repository,
        onSuccess: (starred: Boolean) -> Unit,
        onError: () -> Unit
    ) {
        Networking.githubApi.putStar(
            repository.owner.login,
            repository.name
        ).enqueue(
            object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        val code = response.code()
                        onSuccess(code == 204)
                    } else {
                        onError()
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    onError()
                }
            }
        )
    }
}

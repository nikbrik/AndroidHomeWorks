package com.skillbox.CoroutinesApp.data

import com.skillbox.CoroutinesApp.network.Follower
import com.skillbox.CoroutinesApp.network.Networking
import com.skillbox.CoroutinesApp.network.Repository
import com.skillbox.CoroutinesApp.network.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class GithubAppRepository {
    suspend fun getUser(): User {
        return Networking.githubApi.user()
    }

    suspend fun getFollowers(): List<Follower> {
        return Networking.githubApi.followers()
    }

    suspend fun getRepositoryList(): List<Repository> {
        return withContext(Dispatchers.Default) {
            try {
                Networking.githubApi.repositories()
            } catch (t: Throwable) {
                emptyList()
            }
        }
    }

    suspend fun repositoryIsStarred(
        repository: Repository,
    ): Boolean {

        return suspendCancellableCoroutine { continuation ->

            val isStarredCallback = object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    val code = response.code()
                    continuation.resume(code == 204)
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            }

            val isStarredCall = Networking.githubApi.isStarred(
                repository.owner.login,
                repository.name
            )

            continuation.invokeOnCancellation {
                if (!isStarredCall.isCanceled) isStarredCall.cancel()
            }

            isStarredCall.enqueue(isStarredCallback)
        }
    }

    suspend fun setStar(
        repository: Repository,
        put: Boolean,
    ): Boolean {
        return if (put) {
            putStar(repository)
        } else {
            deleteStar(repository)
        }
    }

    private suspend fun deleteStar(
        repository: Repository,
    ): Boolean {
        return try {
            val response = Networking.githubApi.deleteStar(
                repository.owner.login,
                repository.name
            )
            response.code() != 204
        } catch (t: Throwable) {
            false
        }
    }

    private suspend fun putStar(
        repository: Repository,
    ): Boolean {
        return try {
            val response = Networking.githubApi.putStar(
                repository.owner.login,
                repository.name
            )
            response.code() == 204
        } catch (t: Throwable) {
            false
        }
    }
}

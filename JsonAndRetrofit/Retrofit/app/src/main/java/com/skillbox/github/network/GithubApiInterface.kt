package com.skillbox.github.network

import retrofit2.Call
import retrofit2.http.GET

interface GithubApiInterface {
    @GET("/user")
    fun user(): Call<User>
}

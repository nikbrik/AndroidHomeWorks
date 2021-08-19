package com.skillbox.github.network

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface GithubApiInterface {
    @GET("/user")
    fun user(): Call<User>

    @GET("/repositories")
    fun repositories(): Call<List<Repository>>

    @GET("/user/starred/{owner}/{repo}")
    fun isStarred(
        @Path("owner") owner: String,
        @Path("repo") repositoty: String,
    ): Call<Unit>

    @PUT("/user/starred/{owner}/{repo}")
    fun putStar(
        @Path("owner") owner: String,
        @Path("repo") repositoty: String,
    ): Call<Unit>

    @DELETE("/user/starred/{owner}/{repo}")
    fun deleteStar(
        @Path("owner") owner: String,
        @Path("repo") repositoty: String,
    ): Call<Unit>
}

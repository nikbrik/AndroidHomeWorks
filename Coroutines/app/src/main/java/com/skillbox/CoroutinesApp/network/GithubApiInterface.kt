package com.skillbox.CoroutinesApp.network

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface GithubApiInterface {
    @GET("/user")
    suspend fun user(): User

    @GET("/repositories")
    suspend fun repositories(): List<Repository>

    @GET("/user/starred/{owner}/{repo}")
    fun isStarred(
        @Path("owner") owner: String,
        @Path("repo") repositoty: String,
    ): Call<Unit>

    @PUT("/user/starred/{owner}/{repo}")
    suspend fun putStar(
        @Path("owner") owner: String,
        @Path("repo") repositoty: String,
    ): Response<Unit>

    @DELETE("/user/starred/{owner}/{repo}")
    suspend fun deleteStar(
        @Path("owner") owner: String,
        @Path("repo") repositoty: String,
    ): Response<Unit>

    @GET("/user/followers")
    suspend fun followers(): List<Follower>
}

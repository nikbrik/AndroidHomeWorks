package com.nikbrik.filesapp.network

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

interface GithubApi {
    @GET
    suspend fun getFile(
        @Url url: String
    ): ResponseBody
}

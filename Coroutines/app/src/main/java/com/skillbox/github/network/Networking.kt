package com.skillbox.github.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object Networking {

    var accessToken: String = ""

    private val okhttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(SetAccessTokenInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okhttpClient)
        .build()

    val githubApi: GithubApiInterface
        get() = retrofit.create()
}

package com.skillbox.github.network

import okhttp3.Interceptor
import okhttp3.Response

class SetAccessTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("Authorization", "token ${Networking.accessToken}")
            .build()
        return chain.proceed(newRequest)
    }
}

class SetApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url
        val newUrl = url.newBuilder()
            .addQueryParameter("apikey", "edb54490")
            .build()
        val newRequest = request.newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(newRequest)
    }
}

package com.skillbox.CoroutinesApp.network

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

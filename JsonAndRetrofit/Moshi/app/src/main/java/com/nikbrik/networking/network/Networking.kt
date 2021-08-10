package com.nikbrik.networking.network

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

object Networking {

    val flipperNetworkPlugin = NetworkFlipperPlugin()
    private val client = OkHttpClient.Builder()
        .addNetworkInterceptor(FlipperOkhttpInterceptor(flipperNetworkPlugin))
        .addInterceptor(SetApiKeyInterceptor())
        .build()

    fun getSearchMovieCall(name: String, year: String, type: String): Call {

        val url = HttpUrl.Builder()
            .scheme("http")
            .host("www.omdbapi.com")
            // Обязательные параметры
            .addQueryParameter("s", name)
            // Дополнительные параметры
            .apply {
                if (year.isNotBlank()) addQueryParameter("y", year)
                if (type.isNotBlank()) addQueryParameter("type", type)
            }
            .build()

        val request = Request.Builder()
            .get()
            .url(url)
            .build()

        return client.newCall(request)
    }
}

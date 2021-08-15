package com.skillbox.github.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val url: String,
    val type: String,
    val html_url: String,
)

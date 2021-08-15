package com.skillbox.github.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Repository(
    val id: Int,
    val node_id: String,
    val full_name: String,
    val html_url: String,
    val description: String,
)

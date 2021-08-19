package com.skillbox.github.network

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class User(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val url: String,
    val type: String,
    val html_url: String,
) : Parcelable

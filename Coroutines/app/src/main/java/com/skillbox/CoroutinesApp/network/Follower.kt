package com.skillbox.CoroutinesApp.network

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Follower(
    val login: String,
    val id: Int,
    val node_id: String,
    val avatar_url: String,
    val html_url: String,
    val type: String,
) : Parcelable

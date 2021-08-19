package com.skillbox.github.network

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Repository(
    val id: Int,
    val node_id: String,
    val name: String,
    val full_name: String,
    val html_url: String,
    val description: String?,
    val private: Boolean,
    val owner: User,
) : Parcelable

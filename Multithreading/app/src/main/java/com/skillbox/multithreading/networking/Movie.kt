package com.skillbox.multithreading.networking

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("UUID")
    var id: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: Int,
)

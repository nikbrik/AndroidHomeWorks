package com.skillbox.multithreading.networking

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class Movie(
    val uuid: UUID = UUID.randomUUID(),
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: Int,
    val image: String,
)

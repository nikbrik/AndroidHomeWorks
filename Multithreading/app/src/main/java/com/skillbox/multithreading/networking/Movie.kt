package com.skillbox.multithreading.networking

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class Movie(
    @SerializedName("UUID")
    var uuid: UUID = UUID.randomUUID(),
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: Int,
)

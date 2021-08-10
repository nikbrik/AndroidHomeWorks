package com.nikbrik.networking.recyclerView

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val type: String,
    val name: String,
    val year: String,
    val image: String,
) : Parcelable

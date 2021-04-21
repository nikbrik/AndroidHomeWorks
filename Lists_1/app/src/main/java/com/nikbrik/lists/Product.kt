package com.nikbrik.lists

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Product() : Parcelable {

    @Parcelize
    data class Fruit(
        val photoLink: String,
        val title: String,
        val description: String,
    ) : Product()

    @Parcelize
    data class Vegetable(
        val photoLink: String,
        val title: String,
        val description: String,
    ) : Product()
}

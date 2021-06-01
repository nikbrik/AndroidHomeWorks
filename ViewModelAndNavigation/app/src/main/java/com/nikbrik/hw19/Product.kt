package com.nikbrik.hw19

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

sealed class Product(
    val uuid: UUID = UUID.randomUUID(),
) : Parcelable {

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

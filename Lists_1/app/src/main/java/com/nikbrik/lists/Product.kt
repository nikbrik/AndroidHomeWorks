package com.nikbrik.lists

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

sealed class Product : Parcelable {
    @Parcelize
    data class Fruit(@DrawableRes val imageId: Int) : Product()

    @Parcelize
    data class Vegetable(@DrawableRes val imageId: Int) : Product()
}

package com.nikbrik.lists

import androidx.annotation.DrawableRes

sealed class Product {
    data class Fruits(@DrawableRes val imageId: Int) : Product() {}
    data class Vegetables(@DrawableRes val imageId: Int) : Product() {}
}

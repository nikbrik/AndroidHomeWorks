package com.nikbrik.hw19

import android.content.Context
import kotlin.random.Random

class ProductRepository {
    fun createNewList(context: Context, size: Int): List<Product> {

        val products = mutableListOf<Product>()

        for (i in 1..size) {
            products += Product.Fruit(
                context.getString(R.string.apricot_link),
                context.getString(R.string.apricot_title),
                context.getString(R.string.apricot_description),
            )
            products += Product.Vegetable(
                context.getString(R.string.corn_link),
                context.getString(R.string.corn_title),
                context.getString(R.string.corn_description),
            )
            products += Product.Fruit(
                context.getString(R.string.avocado_link),
                context.getString(R.string.avocado_title),
                context.getString(R.string.avocado_description),
            )
            products += Product.Vegetable(
                context.getString(R.string.squash_link),
                context.getString(R.string.squash_title),
                context.getString(R.string.squash_description),
            )
            products += Product.Fruit(
                context.getString(R.string.lemon_link),
                context.getString(R.string.lemon_title),
                context.getString(R.string.lemon_description),
            )
            products += Product.Vegetable(
                context.getString(R.string.potato_link),
                context.getString(R.string.potato_title),
                context.getString(R.string.potato_description),
            )
            products += Product.Fruit(
                context.getString(R.string.peach_link),
                context.getString(R.string.peach_title),
                context.getString(R.string.peach_description),
            )
            products += Product.Vegetable(
                context.getString(R.string.radish_link),
                context.getString(R.string.radish_title),
                context.getString(R.string.radish_description),
            )
        }
        return products.toList()
    }

    fun addProduct(
        products: List<Product>,
        position: Int,
        title: String,
        description: String,
    ): List<Product> {
        return products.take(position) +
            listOf(
                when (Random.nextInt(2)) {
                    0 -> Product.Fruit("", title, description)
                    1 -> Product.Vegetable("", title, description)
                    else -> error("Random fun must return 0 or 1")
                }
            ) + products.takeLast(products.size - position)
    }

    fun removeProduct(
        products: List<Product>,
        position: Int,
    ): List<Product> {
        return if (products.isNotEmpty() && position >= 0) {
            products.take(position) +
                products.takeLast(products.size - position - 1)
        } else products
    }
}

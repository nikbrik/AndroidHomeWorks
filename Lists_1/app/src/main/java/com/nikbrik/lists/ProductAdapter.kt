package com.nikbrik.lists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlin.random.Random

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    var products = emptyList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return when (viewType) {
            TYPE_FRUIT -> FruitHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.fruit, parent, false),
            )
            TYPE_VEGETABLE -> VegetableHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.vegetable, parent, false),
            )
            else -> error("Incorrect type")
        }
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        when (holder) {
            is FruitHolder -> holder.bind(
                products[position] as? Product.Fruit
                    ?: error("Product at position $position is not fruit")
            )
            is VegetableHolder -> holder.bind(
                products[position] as? Product.Vegetable
                    ?: error("Product at position $position is not vegetable")
            )
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (products[position]) {
            is Product.Fruit -> TYPE_FRUIT
            is Product.Vegetable -> TYPE_VEGETABLE
        }
    }

    fun updateProducts(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    fun addProduct(product: Product, position: Int) {
        products = products.take(position) +
                listOf(product) +
                products.takeLast(products.size - position)
        notifyItemInserted(position)
    }

    abstract class ProductHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photo = view.findViewById<ImageView>(R.id.photo)
        val title = view.findViewById<TextView>(R.id.title)
        val description = view.findViewById<TextView>(R.id.description)

        fun bind(
            photoLink: String,
            title: String,
            description: String,
            @DrawableRes placeHolderId: Int,
        ) {
            this.title.text = title
            this.description.text = description
            Glide.with(itemView)
                .load(if (photoLink.isBlank()) placeHolderId else photoLink)
                .placeholder(placeHolderId)
                .error(R.drawable.no_image_available)
                .into(this.photo)
        }
    }

    class FruitHolder(view: View) : ProductHolder(view) {
        fun bind(fruit: Product.Fruit) {
            super.bind(
                fruit.photoLink,
                fruit.title,
                fruit.description,
                R.drawable.fruits_placeholder,
            )
        }
    }

    class VegetableHolder(view: View) : ProductHolder(view) {
        fun bind(vegetable: Product.Vegetable) {
            super.bind(
                vegetable.photoLink,
                vegetable.title,
                vegetable.description,
                R.drawable.vegetables_placeholder
            )
        }
    }

    companion object {
        const val TYPE_FRUIT = 1
        const val TYPE_VEGETABLE = 2
    }
}

package com.nikbrik.lists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(
    private val products: List<Product>,
) : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return when (viewType) {
            TYPE_FRUIT -> FruitHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.fruit, parent),
            )
            TYPE_VEGETABLE -> VegetableHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.vegetable, parent),
            )
            else -> error("Incorrect type")
        }
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
//        when (holder) {
//            is FruitHolder ->
//            is VegetableHolder ->
//        }
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

    abstract class ProductHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photo = view.findViewById<ImageView>(R.id.photo)
        val title = view.findViewById<TextView>(R.id.title)
        val description = view.findViewById<TextView>(R.id.description)

        fun bind(
            photoLink: String,
            title: String,
            description: String
        ) {
            this.title.text = title
            this.description.text = description
            Glide.with(itemView)
                .load(photoLink)
                .into(photo)
        }
    }

    class FruitHolder(view: View) : ProductHolder(view) {

    }

    class VegetableHolder(view: View) : ProductHolder(view) {

    }

    companion object {
        const val TYPE_FRUIT = 1
        const val TYPE_VEGETABLE = 2
    }
}
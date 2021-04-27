package com.nikbrik.lists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikbrik.lists.databinding.FruitBinding
import com.nikbrik.lists.databinding.VegetableBinding

class ProductAdapter(
    private val onClickAction: (position: Int) -> Unit,
) : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    var products = emptyList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        when (viewType) {
            TYPE_FRUIT -> {
                val binding = FruitBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return FruitHolder(
                    binding,
                    onClickAction,
                )
            }
            TYPE_VEGETABLE -> {
                val binding = VegetableBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return VegetableHolder(
                    binding,
                    onClickAction,
                )
            }
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

    fun removeProduct(position: Int) {
        if (products.isNotEmpty() && position >= 0) {
            products = products.take(position) +
                    products.takeLast(products.size - position - 1)
            notifyItemRemoved(position)
        }
    }

    abstract class ProductHolder(
        view: View,
        onClickAction: (position: Int) -> Unit,
    ) : RecyclerView.ViewHolder(view) {
        lateinit var photo: ImageView
        lateinit var title: TextView
        lateinit var description: TextView

        init {
            view.setOnClickListener {
                onClickAction(bindingAdapterPosition)
            }
        }

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

    class FruitHolder(
        binding: FruitBinding,
        onClickAction: (position: Int) -> Unit,
    ) : ProductHolder(binding.root, onClickAction) {

        init {
            photo = binding.photo
            title = binding.title
            description = binding.description
        }

        fun bind(fruit: Product.Fruit) {
            super.bind(
                fruit.photoLink,
                fruit.title,
                fruit.description,
                R.drawable.fruits_placeholder,
            )
        }
    }

    class VegetableHolder(
        binding: VegetableBinding,
        onClickAction: (position: Int) -> Unit,
    ) : ProductHolder(binding.root, onClickAction) {

        init {
            photo = binding.photo
            title = binding.title
            description = binding.description
        }

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

package com.nikbrik.lists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikbrik.lists.databinding.FruitBinding
import com.nikbrik.lists.databinding.VegetableBinding

class ProductAdapter(
    private val onClickAction: (position: Int) -> Unit,
) : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    val differ = AsyncListDiffer<Product>(this, ProductDiffUtilCallback())

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
                differ.currentList[position] as? Product.Fruit
                    ?: error("Product at position $position is not fruit")
            )
            is VegetableHolder -> holder.bind(
                differ.currentList[position] as? Product.Vegetable
                    ?: error("Product at position $position is not vegetable")
            )
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (differ.currentList[position]) {
            is Product.Fruit -> TYPE_FRUIT
            is Product.Vegetable -> TYPE_VEGETABLE
        }
    }

    fun updateProducts(products: List<Product>) {
        differ.submitList(products)
    }

    fun addProduct(product: Product, position: Int) {
        val newList = differ.currentList.take(position) +
            listOf(product) +
            differ.currentList.takeLast(differ.currentList.size - position)
        updateProducts(newList)
    }

    fun removeProduct(position: Int) {
        if (differ.currentList.isNotEmpty() && position >= 0) {
            updateProducts(
                differ.currentList.take(position) +
                    differ.currentList.takeLast(differ.currentList.size - position - 1)
            )
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

    class ProductDiffUtilCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem::class == newItem::class && oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}

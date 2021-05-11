package com.nikbrik.lists.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.nikbrik.lists.Product
import com.nikbrik.lists.R
import com.nikbrik.lists.databinding.FruitBinding
import com.nikbrik.lists.databinding.FruitGridBinding

class FruitAdapterDelegate(
    private val onClickAction: (position: Int) -> Unit,
) :
    AbsListItemAdapterDelegate<Product.Fruit, Product, FruitAdapterDelegate.FruitHolder>() {

    override fun isForViewType(item: Product, items: MutableList<Product>, position: Int): Boolean {
        return item is Product.Fruit
    }

    override fun onCreateViewHolder(parent: ViewGroup): FruitHolder {
        return FruitHolder(
            FruitBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickAction
        )
    }

    override fun onBindViewHolder(
        item: Product.Fruit,
        holder: FruitHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
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
}

class FruitGridAdapterDelegate(
    private val onClickAction: (position: Int) -> Unit,
) :
    AbsListItemAdapterDelegate<Product.Fruit, Product, FruitGridAdapterDelegate.FruitGridHolder>() {

    override fun isForViewType(item: Product, items: MutableList<Product>, position: Int): Boolean {
        return item is Product.Fruit
    }

    override fun onCreateViewHolder(parent: ViewGroup): FruitGridHolder {
        return FruitGridHolder(
            FruitGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickAction
        )
    }

    override fun onBindViewHolder(
        item: Product.Fruit,
        holder: FruitGridHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class FruitGridHolder(
        binding: FruitGridBinding,
        onClickAction: (position: Int) -> Unit,
    ) : ProductHolder(binding.root, onClickAction) {

        init {
            photo = binding.photo
        }

        fun bind(fruit: Product.Fruit) {
            super.bindGrid(
                fruit.photoLink,
                R.drawable.fruits_placeholder,
            )
        }
    }
}
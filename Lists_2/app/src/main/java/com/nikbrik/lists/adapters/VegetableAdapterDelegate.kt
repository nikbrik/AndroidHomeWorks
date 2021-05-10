package com.nikbrik.lists.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.nikbrik.lists.Product
import com.nikbrik.lists.R
import com.nikbrik.lists.databinding.VegetableBinding

class VegetableAdapterDelegate(
    private val onClickAction: (position: Int) -> Unit,
) :
    AbsListItemAdapterDelegate<Product.Vegetable, Product, VegetableAdapterDelegate.VegetableHolder>() {

    override fun isForViewType(item: Product, items: MutableList<Product>, position: Int): Boolean {
        return item is Product.Vegetable
    }

    override fun onCreateViewHolder(parent: ViewGroup): VegetableHolder {
        return VegetableHolder(
            VegetableBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickAction
        )
    }

    override fun onBindViewHolder(
        item: Product.Vegetable,
        holder: VegetableHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
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

}
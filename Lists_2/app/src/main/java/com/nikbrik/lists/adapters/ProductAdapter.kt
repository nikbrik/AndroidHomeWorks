package com.nikbrik.lists.adapters

import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.paging.PagedListDelegationAdapter
import com.nikbrik.lists.Product
import com.nikbrik.lists.TYPE_GRID
import com.nikbrik.lists.TYPE_LINEAR
import com.nikbrik.lists.TYPE_STAGGERED_GRID

class ProductAdapter(
    val layoutManagerType: Int,
    private val onClickAction: (position: Int) -> Unit,
) : AsyncListDifferDelegationAdapter<Product>(ProductDiffUtilCallback()) {

    val isEmpty: Boolean
        get() = differ.currentList.isEmpty()
    val currentList
        get() = differ.currentList

    init {
        when (layoutManagerType) {
            TYPE_GRID, TYPE_STAGGERED_GRID -> {
                delegatesManager.addDelegate(VegetableGridAdapterDelegate(onClickAction))
                delegatesManager.addDelegate(FruitGridAdapterDelegate(onClickAction))
            }
            else -> {
                delegatesManager.addDelegate(VegetableAdapterDelegate(onClickAction))
                delegatesManager.addDelegate(FruitAdapterDelegate(onClickAction))
            }
        }
    }

    fun updateProducts(products: List<Product>) {
        differ.submitList(products)
    }

    fun addProduct(product: Product, position: Int) {
        differ.currentList.apply {
            val newList = take(position) +
                    listOf(product) +
                    takeLast(size - position)
            updateProducts(newList)
        }
    }

    fun removeProduct(position: Int) {
        if (differ.currentList.isNotEmpty() && position >= 0) {
            differ.currentList.apply {
                updateProducts(
                    take(position) +
                            takeLast(size - position - 1)
                )
            }
        }
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

package com.nikbrik.hw19.adapters

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.nikbrik.hw19.Product
import com.nikbrik.hw19.TYPE_GRID
import com.nikbrik.hw19.TYPE_STAGGERED_GRID

class ProductAdapter(
    val layoutManagerType: Int,
    private val onClickAction: (position: Int) -> Unit,
    private val onLongClickAction: (position: Int) -> Unit,
) : AsyncListDifferDelegationAdapter<Product>(ProductDiffUtilCallback()) {

    val isEmpty: Boolean
        get() = differ.currentList.isEmpty()

    init {
        when (layoutManagerType) {
            TYPE_GRID, TYPE_STAGGERED_GRID -> {
                delegatesManager.addDelegate(
                    VegetableGridAdapterDelegate(
                        onClickAction,
                        onLongClickAction
                    )
                )
                delegatesManager.addDelegate(
                    FruitGridAdapterDelegate(
                        onClickAction,
                        onLongClickAction
                    )
                )
            }
            else -> {
                delegatesManager.addDelegate(
                    VegetableAdapterDelegate(
                        onClickAction,
                        onLongClickAction
                    )
                )
                delegatesManager.addDelegate(FruitAdapterDelegate(onClickAction, onLongClickAction))
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

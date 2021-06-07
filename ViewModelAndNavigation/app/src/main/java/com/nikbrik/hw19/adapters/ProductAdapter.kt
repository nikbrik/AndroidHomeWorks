package com.nikbrik.hw19.adapters

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.nikbrik.hw19.Product
import com.nikbrik.hw19.TYPE_GRID
import com.nikbrik.hw19.TYPE_STAGGERED_GRID

class ProductAdapter(
    val layoutManagerType: Int,
    private val onClickAction: (position: Int) -> Unit,
) : AsyncListDifferDelegationAdapter<Product>(ProductDiffUtilCallback()) {

    val isEmpty: Boolean
        get() = differ.currentList.isEmpty()
    val currentList: MutableList<Product>
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

//    fun updateProducts(products: List<Product>) {
//        differ.submitList(products)
//    }
//
//    fun addProduct(product: Product, position: Int) {
//        differ.currentList.apply {
//            val newList = take(position) +
//                listOf(product) +
//                takeLast(size - position)
//            updateProducts(newList)
//        }
//    }
//
//    fun removeProduct(position: Int) {
//        if (differ.currentList.isNotEmpty() && position >= 0) {
//            differ.currentList.apply {
//                updateProducts(
//                    take(position) +
//                        takeLast(size - position - 1)
//                )
//            }
//        }
//    }

    class ProductDiffUtilCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem::class == newItem::class && oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}

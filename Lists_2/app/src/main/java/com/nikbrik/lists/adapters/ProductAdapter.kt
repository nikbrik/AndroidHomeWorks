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

class ProductAdapter(
    private val onClickAction: (position: Int) -> Unit,
) : PagedListDelegationAdapter<Product>(ProductDiffUtilCallback()) {

//    private val differ = AsyncListDiffer<Product>(this, ProductDiffUtilCallback())

    val isEmpty: Boolean
        get() = currentList?.isEmpty() ?: true
    val currentListToArray
        get() = currentList?.toTypedArray() ?: emptyArray()

    init {
        delegatesManager.addDelegate(VegetableAdapterDelegate(onClickAction))
        delegatesManager.addDelegate(FruitAdapterDelegate(onClickAction))
    }

    fun updateProducts(products: PagedList<Product>) {
        submitList(products)
    }

//    fun addProduct(product: Product, position: Int) {
//        currentList?.run{val newList = take(position) +
//                listOf(product) +
//                takeLast(size - position)
//        updateProducts(apply{this.dataSource = newList})}
//    }
//
//    fun removeProduct(position: Int) {
//        if (differ.currentList.isNotEmpty() && position >= 0) {
//            updateProducts(
//                differ.currentList.take(position) +
//                        differ.currentList.takeLast(differ.currentList.size - position - 1)
//            )
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

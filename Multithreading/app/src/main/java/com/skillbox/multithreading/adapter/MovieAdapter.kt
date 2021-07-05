package com.skillbox.multithreading.adapters

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skillbox.multithreading.adapter.MovieAdapterDelegate
import com.skillbox.multithreading.networking.Movie

class MovieAdapter(
    private val onClickAction: (position: Int) -> Unit,
) : AsyncListDifferDelegationAdapter<Movie>(ProductDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(
            MovieAdapterDelegate(
                onClickAction,
            )
        )
    }

    class ProductDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem::class == newItem::class && oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}

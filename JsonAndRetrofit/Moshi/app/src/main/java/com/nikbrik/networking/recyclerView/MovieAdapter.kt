package com.nikbrik.networking.recyclerView

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MovieAdapter : AsyncListDifferDelegationAdapter<Movie>(MovieItemCallback()) {

    init {
        delegatesManager.addDelegate(MovieAdapterDelegate())
    }

    class MovieItemCallback() : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}

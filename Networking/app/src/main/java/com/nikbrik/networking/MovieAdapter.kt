package com.nikbrik.networking

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.paging.PagedListDelegationAdapter

class MovieAdapter : PagedListDelegationAdapter<Movie>(MovieItemCallback()) {

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

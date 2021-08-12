package com.nikbrik.moshiHW.ui

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.nikbrik.moshiHW.recyclerView.ExtendedMovie

class MovieAdapter : AsyncListDifferDelegationAdapter<ExtendedMovie>(MovieItemCallback()) {

    init {
        delegatesManager.addDelegate(MovieAdapterDelegate())
    }

    class MovieItemCallback() : DiffUtil.ItemCallback<ExtendedMovie>() {
        override fun areItemsTheSame(oldItem: ExtendedMovie, newItem: ExtendedMovie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ExtendedMovie, newItem: ExtendedMovie): Boolean {
            return oldItem == newItem
        }
    }
}

package com.nikbrik.moshiHW.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.nikbrik.moshiHW.databinding.FragmentMovieBinding

class MovieAdapterDelegate : AbsListItemAdapterDelegate<ExtendedMovie, ExtendedMovie, MovieHolder>() {
    override fun isForViewType(item: ExtendedMovie, items: MutableList<ExtendedMovie>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): MovieHolder {
        return MovieHolder(
            FragmentMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(item: ExtendedMovie, holder: MovieHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }
}

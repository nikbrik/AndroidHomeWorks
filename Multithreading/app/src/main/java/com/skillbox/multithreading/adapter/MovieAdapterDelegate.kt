package com.skillbox.multithreading.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.multithreading.databinding.MovieBinding
import com.skillbox.multithreading.networking.Movie

class MovieAdapterDelegate(private val onClickAction: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<Movie, Movie, MovieAdapterDelegate.MovieHolder>() {

    override fun isForViewType(item: Movie, items: MutableList<Movie>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): MovieHolder {
        return MovieHolder(
            MovieBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClickAction,
        )
    }

    override fun onBindViewHolder(item: Movie, holder: MovieHolder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class MovieHolder(
        private val binding: MovieBinding,
        onClickAction: (position: Int) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClickAction(bindingAdapterPosition)
            }
        }

        fun bind(
            movie: Movie,
        ) {
            binding.title.text = movie.title
            binding.year.text = "Year ${movie.year}"
        }
    }
}

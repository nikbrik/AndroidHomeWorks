package com.nikbrik.networking.recyclerView

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nikbrik.networking.R
import com.nikbrik.networking.databinding.FragmentMovieBinding

class MovieHolder(val binding: FragmentMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    init {
    }

    fun bind(
        movie: Movie?
    ) {
        binding.name.text = movie?.name ?: "loading..."
        binding.year.text = movie?.year ?: ""
        binding.poster.load(movie?.image) {
            placeholder(R.drawable.ic_launcher_foreground)
            error(R.drawable.ic_launcher_foreground)
        }
        binding.imdbID.text = movie?.id ?: ""
        binding.type.text = movie?.type ?: ""
    }
}

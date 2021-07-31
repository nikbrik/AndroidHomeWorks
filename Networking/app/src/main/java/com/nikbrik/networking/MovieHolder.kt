package com.nikbrik.networking

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nikbrik.networking.databinding.FragmentMovieBinding

class MovieHolder(val binding: FragmentMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    init {
    }

    fun bind(
        movie: Movie
    ) {
        binding.name.text = movie.name
        binding.year.text = movie.year
        binding.poster.load(movie.image)
    }
}

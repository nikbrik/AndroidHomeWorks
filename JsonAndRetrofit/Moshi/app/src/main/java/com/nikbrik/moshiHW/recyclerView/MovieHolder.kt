package com.nikbrik.moshiHW.recyclerView

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nikbrik.moshiHW.R
import com.nikbrik.moshiHW.databinding.FragmentMovieBinding
import com.squareup.moshi.Moshi
import timber.log.Timber

class MovieHolder(val binding: FragmentMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    init {
    }

    fun bind(
        movie: ExtendedMovie?
    ) {
        binding.name.text = movie?.name ?: "loading..."
        binding.year.text = movie?.year ?: ""
        binding.poster.load(movie?.image) {
            placeholder(R.drawable.ic_launcher_foreground)
            error(R.drawable.ic_launcher_foreground)
        }
        binding.imdbID.text = movie?.id ?: ""
        binding.type.text = movie?.type ?: ""
        binding.genre.text = movie?.genre ?: ""
        binding.rated.text = movie?.rated?.toString() ?: Rated.NA.toString()
        binding.rating.text = movie?.ratings?.toString()
        binding.rate.setOnClickListener {
            movie?.userRating = true
            it.isVisible = false
            logToJson(movie)
        }
    }

    private fun logToJson(movie: ExtendedMovie?) {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(ExtendedMovie::class.java).nonNull()
        Timber.d(adapter.toJson(movie))
    }
}

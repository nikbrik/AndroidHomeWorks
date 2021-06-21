package com.skillbox.multithreading

import com.skillbox.multithreading.networking.Movie
import com.skillbox.multithreading.networking.Network

class MoviesRepository {

    private fun getPredefinedMoviesID(): List<String> {
        return listOf(
            "tt0111161",
            "tt0133093",
            "tt0137523",
            "tt0109830",
            "tt0068646",
            "tt0167261",
        )
    }

    fun fetchMovies(
        onMoviesFetched: (movies: List<Movie>) -> Unit,
    ) {
        val idList = getPredefinedMoviesID()
        val movieList = mutableListOf<Movie>()
        Thread {
            val threads = idList.map { id ->
                Thread {
                    Network.getMovieById(id)?.let { movie ->
                        movieList.add(movie)
                    }
                }
            }
            threads.forEach { thread ->
                thread.start()
            }
            threads.forEach { thread ->
                thread.join()
            }
            onMoviesFetched(movieList)
        }.start()
    }
}

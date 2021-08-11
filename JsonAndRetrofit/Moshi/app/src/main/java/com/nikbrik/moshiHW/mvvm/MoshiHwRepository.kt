package com.nikbrik.moshiHW.mvvm

import com.nikbrik.moshiHW.RatingCustomAdapter
import com.nikbrik.moshiHW.network.Networking
import com.nikbrik.moshiHW.recyclerView.ExtendedMovie
import com.nikbrik.moshiHW.recyclerView.Movie
import com.squareup.moshi.Moshi
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber
import java.io.IOException

class MoshiHwRepository {
    private var errorMessage = ""
    private var extendedMovieCount = 0
    private lateinit var extendedMovieCallback: (movies: List<ExtendedMovie>, errorMessage: String) -> Unit
    private val extendedMovieList = mutableListOf<ExtendedMovie>()

    fun searchMovie(
        name: String,
        year: String,
        type: String,
        searchCallback: (movies: List<ExtendedMovie>, errorMessage: String) -> Unit
    ) {

        val call = Networking.getSearchMovieCall(name, year, type)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                errorMessage = "Execute request error - ${e.message}"
                searchCallback(emptyList(), errorMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBodyString = response.body?.string().orEmpty()
                val movies = parseMovieResponseBody(responseBodyString)
                if (movies.isEmpty()) {
                    errorMessage = "not found by name"
                    searchCallback(emptyList(), errorMessage)
                }
                extendedMovieCount = movies.size
                extendedMovieCallback = searchCallback
                movies.forEach {
                    movieByIdTo(it.id)
                }
            }
        })
    }

    private fun addToExtendedMovieList(extendedMovie: ExtendedMovie) {
        extendedMovieList.add(extendedMovie)
        if (extendedMovieList.size == extendedMovieCount) {
            extendedMovieCallback(
                extendedMovieList.toList(),
                ""
            )
        }
    }

    private fun movieByIdTo(imdbID: String) {
        val call = Networking.getMovieCallById(imdbID)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Timber.e(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBodyString = response.body?.string().orEmpty()
                val movie = extendedMovieFromJson(responseBodyString)
                movie?.let { addToExtendedMovieList(it) } ?: extendedMovieCallback(
                    emptyList(),
                    "parse response error"
                )
            }
        })
    }

    private fun parseMovieResponseBody(responseString: String): List<Movie> {
        return try {
            val movies = JSONObject(responseString).getJSONArray("Search")
            (0 until movies.length()).map { index ->

                val movie = movieFromJson(movies.getString(index))
                    ?: throw JSONException("Can not convert object to movie with Moshi")

                movie
            }
        } catch (e: JSONException) {
            errorMessage = "parse response error = ${e.message}"
            emptyList()
        }
    }

    private fun movieFromJson(jsonString: String): Movie? {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(Movie::class.java).nonNull()
        return adapter.fromJson(jsonString)
    }

    private fun extendedMovieFromJson(jsonString: String): ExtendedMovie? {
        val moshi = Moshi.Builder()
            .add(RatingCustomAdapter())
            .build()
        val adapter = moshi.adapter(ExtendedMovie::class.java).nonNull()
        return adapter.fromJson(jsonString)
    }
}

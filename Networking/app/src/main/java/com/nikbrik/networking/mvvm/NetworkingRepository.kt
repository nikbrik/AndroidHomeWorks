package com.nikbrik.networking.mvvm

import com.nikbrik.networking.network.Networking
import com.nikbrik.networking.recyclerView.Movie
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class NetworkingRepository {
    private var errorMessage = ""

    fun searchMovie(
        name: String,
        year: String,
        type: String,
        searchCallback: (movies: List<Movie>, errorMessage: String) -> Unit
    ) {

        val call = Networking.getSearchMovieCall(name, year, type)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                errorMessage = "Execute request error - ${e.message}"
                searchCallback(emptyList<Movie>(), errorMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBodyString = response.body?.string().orEmpty()
                val movies = parseMovieResponseBody(responseBodyString)
                searchCallback(movies, errorMessage)
            }
        })

//        try {
        // Ошибка выполнения на главном потоке
//            val response = Networking.getSearchMovieCall(name, year, type).execute()
//            response.isSuccessful
//        } catch (e: IOException) {
//
//        }
    }

    private fun parseMovieResponseBody(responseString: String): List<Movie> {
        return try {
            val movies = JSONObject(responseString).getJSONArray("Search")
            (0 until movies.length()).map { index ->
                val jsonMovie = movies.getJSONObject(index)
                Movie(
                    id = jsonMovie.getString("imdbID"),
                    type = jsonMovie.getString("Type"),
                    name = jsonMovie.getString("Title"),
                    year = jsonMovie.getString("Year"),
                    image = jsonMovie.getString("Poster"),
                )
            }
        } catch (e: JSONException) {
            errorMessage = "parse response error = ${e.message}"
            emptyList()
        }
    }
}

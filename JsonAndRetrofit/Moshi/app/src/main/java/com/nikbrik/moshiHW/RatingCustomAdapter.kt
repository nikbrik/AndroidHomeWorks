package com.nikbrik.moshiHW

import android.os.Parcelable
import com.nikbrik.moshiHW.recyclerView.ExtendedMovie
import com.nikbrik.moshiHW.recyclerView.Rated
import com.nikbrik.moshiHW.recyclerView.Rating
import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

class RatingCustomAdapter {

    @FromJson
    fun fromJson(rawMovie: RawMovie): ExtendedMovie {
        return ExtendedMovie(
            rawMovie.id,
            rawMovie.type,
            rawMovie.name,
            rawMovie.year,
            rawMovie.image,
            rawMovie.rated,
            rawMovie.genre,
            rawMovie.ratings.map { it.source to it.value }.toMap(),
        )
    }

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class RawMovie(
        @Json(name = "imdbID")
        val id: String,

        @Json(name = "Type")
        val type: String,

        @Json(name = "Title")
        val name: String,

        @Json(name = "Year")
        val year: String = "",

        @Json(name = "Poster")
        val image: String = "",

        @Json(name = "Rated")
        val rated: Rated = Rated.NA,

        @Json(name = "Genre")
        val genre: String = "",

        @Json(name = "Ratings")
        val ratings: List<Rating> = emptyList()

    ) : Parcelable
}

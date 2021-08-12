package com.nikbrik.moshiHW.recyclerView

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Movie(

    @Json(name = "imdbID")
    val id: String,

    @Json(name = "Title")
    val name: String,

) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class ExtendedMovie(
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
    val ratings: Map<String, String> = emptyMap(),

    var userRating: Boolean = false,

) : Parcelable

enum class Rated {
    @Json(name = "N/A")
    NA,

    @Json(name = "G")
    G,

    @Json(name = "PG")
    PG,

    @Json(name = "PG-13")
    PG_13,

    @Json(name = "R")
    R,

    @Json(name = "NC-17")
    NC_17,

    @Json(name = "Approved")
    AP,

    @Json(name = "TV-G")
    TV_G,

    @Json(name = "TV-PG")
    TV_PG,

    @Json(name = "Not Rated")
    NR,
}

@JsonClass(generateAdapter = true)
@Parcelize
data class Rating(

    @Json(name = "Source")
    val source: String,

    @Json(name = "Value")
    val value: String,

) : Parcelable

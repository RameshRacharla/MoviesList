package com.ramesh.movieslist.data.remote

import com.google.gson.annotations.SerializedName

data class MovieListDetails(
    @field:SerializedName("Title")
    val title: String? = null,
    @field:SerializedName("Year")
    val year: String? = null,
    @field:SerializedName("Released")
    val released: String? = null,
    @field:SerializedName("Runtime")
    val runtime: String? = null,
    @field:SerializedName("Genre")
    val genre: String? = null,
    @field:SerializedName("Writer")
    val writer: String? = null,
    @field:SerializedName("Actors")
    val actors: String? = null,
    @field:SerializedName("Plot")
    val plot: String? = null,
    @field:SerializedName("Poster")
    val poster: String? = null
)

package com.ramesh.movieslist.data.remote

import com.google.gson.annotations.SerializedName

data class Movielist(


    @field:SerializedName("Title")
    val title: String? = null,
    @field:SerializedName("Year")
    val year: String? = null,
    @field:SerializedName("imdbID")
    val imdbID: String? = null,
    @field:SerializedName("Type")
    val Type: String? = null,
    @field:SerializedName("Poster")
    val poster: String? = null
)

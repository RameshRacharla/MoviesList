package com.ramesh.movieslist.data.remote

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("Search")
    val list: List<Movielist>? = null
)
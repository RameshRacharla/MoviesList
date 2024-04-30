package com.ramesh.movieslist.data.remote

import com.google.gson.annotations.SerializedName

data class DataReq(
    @field:SerializedName("apikey")
    val apikey: String? = null,
    @field:SerializedName("i")
    val i: String? = null,
    @field:SerializedName("type")
    val type: String? = null
)
package com.ramesh.movieslist.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NetworkService {
    //1) Movies search  :http://www.omdbapi.com/?type=movie&apikey=a1b5f9ec&s=abc
//
//2) Movie Details :  http://www.omdbapi.com/?i=tt0281534&apikey=a1b5f9ec
    @GET(Endpoints.GET_MOVIES)
    suspend fun getMovielist(
        @Query("type") id: String,
        @Query("apikey") apikey: String,
        @Query("s") s: String
    ): Response<MovieResponse>

    @GET(Endpoints.GET_MOVIES_LIST)
    suspend fun getMoviesListDetails(
        @Query("i") i: String,
        @Query("apikey") apikey: String
    ): Response<MovieListDetails>

}
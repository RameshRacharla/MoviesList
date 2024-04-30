package com.ramesh.movieslist.data.repository

import com.ramesh.movieslist.data.remote.MovieListDetails
import com.ramesh.movieslist.data.remote.MovieResponse
import com.ramesh.movieslist.data.remote.Movielist
import com.ramesh.movieslist.data.remote.NetworkService
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val networkService: NetworkService
) {
    suspend fun getMovielist(
        type: String, apikey: String, s: String
    ): Response<MovieResponse> {
        return networkService.getMovielist(type, apikey, s)
    }

    suspend fun getMovielistDetails(
        i: String, apikey: String
    ): Response<MovieListDetails> {
        return networkService.getMoviesListDetails(i, apikey)
    }
}
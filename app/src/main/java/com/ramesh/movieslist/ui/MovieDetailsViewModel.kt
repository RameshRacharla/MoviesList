package com.ramesh.movieslist.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ramesh.movieslist.data.remote.MovieListDetails
import com.ramesh.movieslist.data.repository.MovieRepository
import com.ramesh.movieslist.ui.base.BaseViewModel
import com.ramesh.movieslist.utils.NetworkHelper
import com.ramesh.movieslist.utils.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 *  Author : @Ramesh Racharla
 *
 * */
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    networkHelper: NetworkHelper, private val movieRepository: MovieRepository
) : BaseViewModel(networkHelper) {
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val getDataResponse: MutableLiveData<Resource<MovieListDetails>> = MutableLiveData()
    override fun onCreate() {
    }

    fun getEmployeeDetails(i: String, apikey: String) = viewModelScope.launch {
        if (checkInternetConnectionWithMessage()) {
            loading.postValue(true)

            val response = movieRepository.getMovielistDetails(i, apikey)
            if (response.isSuccessful) {
                getDataResponse.postValue(Resource.success(response.body()))
            } else {
                responseFailureError("Something went Wrong!")
            }
            loading.postValue(false)
        }
    }
}
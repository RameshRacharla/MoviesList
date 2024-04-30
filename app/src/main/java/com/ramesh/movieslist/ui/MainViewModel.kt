package com.ramesh.movieslist.ui


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ramesh.movieslist.data.remote.MovieResponse
import com.ramesh.movieslist.data.remote.Movielist
import com.ramesh.movieslist.data.repository.MovieRepository
import com.ramesh.movieslist.ui.base.BaseViewModel
import com.ramesh.movieslist.utils.NetworkHelper
import com.ramesh.movieslist.utils.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    networkHelper: NetworkHelper, private val userRepository: MovieRepository
) : BaseViewModel(networkHelper) {
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val getDataResponse: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    override fun onCreate() {
    }

    fun getMovieData(type: String, apikey: String, s: String) = viewModelScope.launch {
        if (checkInternetConnectionWithMessage()) {
            loading.postValue(true)

            val response = userRepository.getMovielist(
                type, apikey, s
            )
            if (response.isSuccessful) {
                getDataResponse.postValue(Resource.success(response.body()))
            } else {
                responseFailureError("Something went Wrong!")
            }
            loading.postValue(false)
        }
    }
}
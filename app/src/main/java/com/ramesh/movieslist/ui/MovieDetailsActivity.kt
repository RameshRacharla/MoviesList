package com.ramesh.movieslist.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.ramesh.movieslist.R
import com.ramesh.movieslist.data.remote.MovieListDetails
import com.ramesh.movieslist.databinding.ActivityMoviedetailsBinding
import com.ramesh.movieslist.ui.base.BaseActivity
import com.ramesh.movieslist.utils.common.Constants
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 *  Author : @Ramesh Racharla
 *
 * */
@AndroidEntryPoint
class MovieDetailsActivity : BaseActivity<MovieDetailsViewModel>() {
    companion object {
        fun newIntent(context: Context, id: String): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(Constants.id, id)
            return intent
        }
    }

    private lateinit var empDetails: MovieListDetails
    private lateinit var binding: ActivityMoviedetailsBinding
    override val viewModel: MovieDetailsViewModel by viewModels()
    override fun provideLayoutId(): View {
        binding = ActivityMoviedetailsBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun setupView(savedInstanceState: Bundle?) {
        val id = intent.getStringExtra(Constants.id)
        viewModel.getEmployeeDetails(id!!, Constants.apikey)
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.getDataResponse.observe(this, Observer {
            empDetails = it.data!!

            empDetails.run {
                binding.textRelease.text = released.toString()
                binding.textRuntime.text = runtime.toString()
                binding.textTitle.text = title.toString()
                Glide.with(this@MovieDetailsActivity).load(poster).placeholder(
                    R.mipmap.ic_launcher
                ).into(binding.imageLogo)

            }
        })

    }

}
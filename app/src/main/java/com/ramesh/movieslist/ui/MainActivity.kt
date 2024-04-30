package com.ramesh.movieslist.ui


import android.os.Bundle
import android.view.View

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ramesh.movieslist.data.remote.Movielist
import com.ramesh.movieslist.databinding.ActivityMainBinding
import com.ramesh.movieslist.ui.base.BaseActivity
import com.ramesh.movieslist.utils.common.Constants
import com.ramesh.movieslist.utils.common.Toaster
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>() {
    private lateinit var movieAdapter: MovieAdapter
    private var dataList: ArrayList<Movielist?> = ArrayList<Movielist?>()
    private lateinit var binding: ActivityMainBinding
    override val viewModel: MainViewModel by viewModels()
    override fun provideLayoutId(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun setupView(savedInstanceState: Bundle?) {
        movieAdapter = MovieAdapter(this, dataList)
        binding.recyclerview.apply {

            adapter = movieAdapter
        }
        binding.buttonSearch.setOnClickListener {
            val staggeredGridLayoutManager =
                StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            binding.recyclerview.layoutManager = staggeredGridLayoutManager

            if (binding.edittextSearch.text.toString().isNotEmpty()) {
                viewModel.getMovieData(
                    Constants.movie,
                    Constants.apikey,
                    binding.edittextSearch.text.toString()
                )
            } else {
                Toaster.show(this, "Please Enter Text")
            }
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.getDataResponse.observe(this, Observer {
            try {


                dataList = (it.data?.list as ArrayList<Movielist?>?)!!
                it.data.run {
                    if (dataList.isNotEmpty()) {
                        movieAdapter.onAddItems(dataList)
                        binding.recyclerview.visibility = View.VISIBLE
                        binding.textNo.visibility = View.GONE
                    } else {
                        binding.recyclerview.visibility = View.GONE
                        binding.textNo.visibility = View.VISIBLE
                    }
                }
            } catch (e: Exception) {
                Toaster.show(this,"Too many results, Please try after some time.. ")
            }
        })

    }

}
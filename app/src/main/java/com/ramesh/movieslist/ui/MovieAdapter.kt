package com.ramesh.movieslist.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ramesh.movieslist.R
import com.ramesh.movieslist.data.remote.Movielist
import com.ramesh.movieslist.databinding.AdapterMovieBinding

class MovieAdapter(
    val context: Context, var movielist: ArrayList<Movielist?>
) : RecyclerView.Adapter<MovieAdapter.DataViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): DataViewHolder {
        val binding =
            AdapterMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DataViewHolder, position: Int
    ) {

        try {
            holder.binding.textTitle.text = movielist[position]?.title
            holder.binding.textType.text = movielist[position]?.Type
            holder.binding.textYear.text = movielist[position]?.year
            Glide.with(context).load(movielist[position]?.poster).placeholder(
                R.mipmap.ic_launcher
            ).into(holder.binding.imagePoster)
            holder.itemView.setOnClickListener {
                val id = movielist[position]?.imdbID
                val intent = MovieDetailsActivity.newIntent(
                    context,
                    id!!
                )
                context.startActivity(intent)
            }
        } catch (e: Exception) {
            e.stackTrace
        }

    }


    fun onAddItems(list: ArrayList<Movielist?>) {
        this.movielist = list
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return movielist.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    class DataViewHolder(val binding: AdapterMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {}

}


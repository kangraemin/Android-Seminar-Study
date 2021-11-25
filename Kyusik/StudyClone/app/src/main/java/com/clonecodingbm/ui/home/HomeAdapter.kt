package com.clonecodingbm.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.clonecodingbm.R
import com.clonecodingbm.data.di.GlideApp
import com.clonecodingbm.data.remote.home.Movie
import com.clonecodingbm.databinding.ItemMovieBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MovieViewHolder>() {
    private var movies: List<Movie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            itemView.setOnClickListener {
                val directions =
                    HomeFragmentDirections.actionNavFragmentToDetailsFragment(movie.id!!)
                it.findNavController().navigate(directions)
            }
            binding.apply {
                GlideApp.with(ivPoster)
                    .load("https://image.tmdb.org/t/p/w500${movie.backdropPath}")
                    .placeholder(R.drawable.ic_image_placeholder)
                    .error(R.drawable.ic_image_error)
                    .into(ivPoster)
                tvTitle.text = movie.title
                tvReleaseDate.text = movie.releaseDate
                tvOverview.text = movie.overview
            }
        }
    }
}
package com.clonecodingbm.ui.home.detail

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.clonecodingbm.R
import com.clonecodingbm.data.di.GlideApp
import com.clonecodingbm.data.remote.base.Status
import com.clonecodingbm.databinding.FragmentDetailsBinding
import com.clonecodingbm.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment :
    BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {

    private val viewModel: DetailsViewModel by viewModels()

    override fun init() {
        requireActivity().window.decorView.systemUiVisibility = 0

        binding.ibBack.setOnClickListener {
            it.findNavController().popBackStack()
        }

        viewModel.movie.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    val movieData = it.data
                    showLoading(false, binding.pbLoading)

                    GlideApp.with(binding.ivBackdrop)
                        .load("https://image.tmdb.org/t/p/original${movieData?.backdropPath}")
                        .placeholder(R.drawable.ic_image_placeholder)
                        .error(R.drawable.ic_image_error)
                        .into(binding.ivBackdrop)

                    GlideApp.with(binding.ivPoster)
                        .load("https://image.tmdb.org/t/p/w500${movieData?.posterPath}")
                        .placeholder(R.drawable.ic_image_placeholder)
                        .error(R.drawable.ic_image_error)
                        .into(binding.ivPoster)

                    binding.tvTitle.text = movieData?.title

                    if (movieData?.genres != null && movieData.genres.isNotEmpty()) {
                        val genres = movieData.genres.joinToString(
                            separator = " | ",
                            transform = { genre -> genre.name })
                        binding.tvGenres.text = genres
                    } else {
                        binding.tvGenres.visibility = View.GONE
                    }

                    if (movieData?.runtime != null) {
                        binding.tvRuntime.text =
                            getString(R.string.format_runtime, movieData.runtime)
                    } else {
                        binding.tvRuntime.visibility = View.GONE
                    }

                    if (movieData?.releaseDate != null && movieData.releaseDate.isNullOrBlank()) {
                        binding.tvReleaseDate.text = movieData.releaseDate
                    } else {
                        binding.tvReleaseDate.visibility = View.GONE
                    }

                    movieData?.voteAverage?.let { rating ->
                        binding.rbRating.rating = (rating / 2).toFloat()
                    }

                    binding.tvVoteCount.text = movieData?.voteCount.toString()
                    binding.tvOverview.text = movieData?.overview
                }
                Status.LOADING -> {
                    showLoading(false, binding.pbLoading)
                }
                Status.ERROR -> {
                    showLoading(true, binding.pbLoading)
                    showToast("상세 정보가 없음")
                    findNavController().popBackStack()
                }
            }
        })
    }

}
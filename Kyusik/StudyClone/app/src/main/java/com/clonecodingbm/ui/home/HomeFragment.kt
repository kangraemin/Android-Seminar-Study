package com.clonecodingbm.ui.home

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.clonecodingbm.R
import com.clonecodingbm.data.remote.base.Status
import com.clonecodingbm.databinding.FragmentHomeBinding
import com.clonecodingbm.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var homeAdapterHorizontal: HomeAdapter
    private lateinit var homeAdapter: HomeAdapter

    override fun init() {
//        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//  set status text dark

        homeAdapterHorizontal = HomeAdapter()
        binding.rvMovieHorizontal.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMovieHorizontal.adapter = homeAdapterHorizontal
        homeAdapter = HomeAdapter()
        binding.rvMovie.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovie.adapter = homeAdapter

        viewModel.position.observe(viewLifecycleOwner, {
            Log.e(TAG, "position: $it")
            binding.rvMovieHorizontal.smoothScrollToPosition(it)
        })

        viewModel.trendingMovies.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    showLoading(false, binding.pbLoading)
                    homeAdapter.setMovies(it.data!!)
                    homeAdapterHorizontal.setMovies(it.data)
                }
                Status.ERROR -> {
                    showLoading(false, binding.pbLoading)
                    Snackbar.make(requireView(), it.message!!, Snackbar.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    showLoading(true, binding.pbLoading)
                }
            }
        })

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.searchFragment -> {
                    findNavController().navigate(R.id.action_navFragment_to_searchFragment)
                }
                R.id.favoriteFragment -> {
                    findNavController().navigate(R.id.action_navFragment_to_favoriteFragment)
                }
                R.id.orderListFragment -> {
                    findNavController().navigate(R.id.action_navFragment_to_orderListFragment)
                }
                R.id.myPageFragment -> {
                    findNavController().navigate(R.id.action_navFragment_to_myPageFragment)
                }
            }
            true
        }
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}
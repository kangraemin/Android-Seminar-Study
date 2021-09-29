package com.terry.delivery.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.terry.delivery.base.BaseFragment
import com.terry.delivery.databinding.FragmentSearchBinding

/*
 * Created by Taehyung Kim on 2021-08-29
 */
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel by activityViewModels<SearchViewModel>()

    private val searchRankHighAdapter = SearchRankAdapter()
    private val searchRankLowAdapter = SearchRankAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return

        observeLiveData()
        initToolbar(binding)
        initRankList(binding)
    }

    private fun initToolbar(binding: FragmentSearchBinding) {
        with(binding.tbSearch) {
            setupWithNavController(findNavController())
        }
    }

    private fun observeLiveData() {
        viewModel.searchRankData.observe(viewLifecycleOwner) { rankList ->
            searchRankHighAdapter.submitList(rankList.slice(0 until 5))
            searchRankLowAdapter.submitList(rankList.slice(5 until 10))
        }
    }

    private fun initRankList(binding: FragmentSearchBinding) {
        with(binding.rvSearchRankHigh) {
            adapter = searchRankHighAdapter
            layoutManager = LinearLayoutManager(requireContext())
            isNestedScrollingEnabled = false
        }

        with(binding.rvSearchRankLow) {
            adapter = searchRankLowAdapter
            layoutManager = LinearLayoutManager(requireContext())
            isNestedScrollingEnabled = false
        }

        viewModel.initDebugRankData()
    }
}
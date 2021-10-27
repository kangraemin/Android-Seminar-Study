package com.terry.delivery.features.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.terry.delivery.R
import com.terry.delivery.base.BaseFragment
import com.terry.delivery.databinding.FragmentSearchBinding
import java.text.SimpleDateFormat
import java.util.*

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
        bindViews(binding)
        initRankTime(binding)
        initRankList(binding)
        initSearchTextListener(binding)
    }

    private fun bindViews(binding: FragmentSearchBinding) {
        with(binding) {
            ivCancelSearch.setOnClickListener {
                fun clearSearchText() = binding.etSearch.setText("")

                clearSearchText()
            }
        }
    }

    private fun observeLiveData() {
        viewModel.searchRankData.observe(viewLifecycleOwner) { rankList ->
            searchRankHighAdapter.submitList(rankList.ranking.slice(0 until 5))
            searchRankLowAdapter.submitList(rankList.ranking.slice(5 until 10))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initRankTime(binding: FragmentSearchBinding) {
        val date = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("MM.dd HH:00", Locale.ROOT)

        binding.tvSearchRankTime.text = "${dateFormat.format(date)} 기준"
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

        viewModel.initDebugRankData(resources.openRawResource(R.raw.rank_data))
    }

    private fun initSearchTextListener(binding: FragmentSearchBinding) {
        binding.etSearch.addTextChangedListener { text ->
            if (text != null && text.isNotEmpty()) {
                binding.groupSearch.visibility = View.VISIBLE
            }

            if (text == null || text.isEmpty()) {
                binding.groupSearch.visibility = View.GONE
            }
        }
    }
}
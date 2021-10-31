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
import com.terry.delivery.util.SnackbarUtil
import timber.log.Timber
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

        observeLiveData()
        bindViews()
        initRankTime()
        initRankList()
        initSearchTextListener()
    }

    private fun bindViews() {
        with(getViewBinding()) {
            ivCancelSearch.setOnClickListener {
                fun clearSearchText() = this.etSearch.setText("")

                clearSearchText()
            }
        }
    }

    private fun observeLiveData() {
        viewModel.searchRankData.observe(viewLifecycleOwner) { rankList ->
            searchRankHighAdapter.submitList(rankList.ranking.slice(0 until 5))
            searchRankLowAdapter.submitList(rankList.ranking.slice(5 until 10))
        }

        viewModel.queryResult.observe(viewLifecycleOwner) { queryList ->
            Timber.d(queryList.toString())
        }

        viewModel.failMessage.observe(viewLifecycleOwner) { msg ->
            view?.let { SnackbarUtil.showErrorMessage(it, msg) }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initRankTime() {
        val date = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("MM.dd HH:00", Locale.ROOT)

        getViewBinding().tvSearchRankTime.text = "${dateFormat.format(date)} 기준"
    }

    private fun initRankList() {
        with(getViewBinding().rvSearchRankHigh) {
            adapter = searchRankHighAdapter
            layoutManager = LinearLayoutManager(requireContext())
            isNestedScrollingEnabled = false
        }

        with(getViewBinding().rvSearchRankLow) {
            adapter = searchRankLowAdapter
            layoutManager = LinearLayoutManager(requireContext())
            isNestedScrollingEnabled = false
        }

        viewModel.initDebugRankData(resources.openRawResource(R.raw.rank_data))
    }

    private fun initSearchTextListener() {
        getViewBinding().etSearch.addTextChangedListener { text ->
            if (text != null && text.isNotEmpty()) {
                getViewBinding().groupSearch.visibility = View.VISIBLE
            }

            if (text == null || text.isEmpty()) {
                getViewBinding().groupSearch.visibility = View.GONE
            }

            if (text != null && text.count() >= 2) {
                viewModel.searchItem(text.toString())
            }
        }
    }
}
package com.terry.delivery.features.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.terry.delivery.R
import com.terry.delivery.base.BaseFragment
import com.terry.delivery.data.local.model.SearchHistory
import com.terry.delivery.databinding.FragmentSearchBinding
import com.terry.delivery.util.SnackbarUtil
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/*
 * Created by Taehyung Kim on 2021-08-29
 */
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel by activityViewModels<SearchViewModel>()

    private val delayHandler by lazy { Handler(Looper.getMainLooper()) }
    private val searchQueryRunnable: (String) -> Runnable = { searchQuery ->
        Runnable { viewModel.searchItem(searchQuery) }
    }

    private val searchRankHighAdapter = SearchRankAdapter onItemClick@{
        performSearch(it)
    }
    private val searchRankLowAdapter = SearchRankAdapter onItemClick@{
        performSearch(it)
    }
    private val searchListAdapter = SearchListAdapter onItemClick@{
        performSearch(it)
    }

    private val historyTagList = ArrayDeque<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()
        bindViews()
        initRankTime()
        initRankList()
        initSearchList()
        initSearchTextListener()
    }

    private fun bindViews() {
        with(getViewBinding()) {
            ivCancelSearch.setOnClickListener {
                fun clearSearchText() = this.etSearch.setText("")

                clearSearchText()
            }

            cpSearchDeleteAll.setOnClickListener {
                viewModel.deleteSearchHistoryAll()
                cgSearchHistory.removeAllViews()
                historyTagList.clear()
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
            val lastSize = if (queryList.size >= 20) 20 else queryList.size
            searchListAdapter.submitList(queryList.slice(0 until lastSize))
        }

        viewModel.failMessage.observe(viewLifecycleOwner) { msg ->
            view?.let { SnackbarUtil.showErrorMessage(it, msg) }
        }

        lifecycleScope.launch {
            viewModel.searchHistories.collect { searchHistoryList ->
                addSearchHistoryList(searchHistoryList)
                this.cancel()
            }
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

    private fun initSearchList() {
        with(getViewBinding().rvSearchList) {
            adapter = searchListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initSearchTextListener() {
        getViewBinding().etSearch.addTextChangedListener { text ->
            if (text == null || text.isEmpty()) {
                searchListAdapter.submitList(emptyList())
            }

            if (text != null && text.isNotEmpty()) {
                getViewBinding().groupSearch.visibility = View.VISIBLE
            }

            if (text == null || text.isEmpty()) {
                getViewBinding().groupSearch.visibility = View.GONE
            }

            if (text != null && text.count() >= 2) {
                delayHandler.removeCallbacksAndMessages(null)

                delayHandler.postDelayed(
                    searchQueryRunnable(text.toString()),
                    300L
                )

            }
        }

        getViewBinding().etSearch.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(v.text.toString())
                return@setOnEditorActionListener true
            }

            return@setOnEditorActionListener false
        }
    }

    private fun performSearch(searchQuery: String) {
        Flowable.just(saveSearchHistory(searchQuery))
            .throttleFirst(800L, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addTo(disposable)
    }

    private fun saveSearchHistory(searchQuery: String) {
        viewModel.saveSearchHistory(searchQuery)
        addSearchHistoryTag(searchQuery, true)
    }

    private fun addSearchHistoryList(list: List<SearchHistory>) {
        list.forEach { addSearchHistoryTag(it.title) }
    }

    private fun addSearchHistoryTag(searchTitle: String, shouldAddReverse: Boolean = false) {
        val chip = Chip(requireContext()).apply {
            text = searchTitle
            closeIcon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_cancel_24)
            isCloseIconVisible = true
        }

        bindsChipClickListener(chip, searchTitle)

        addChipToGroup(shouldAddReverse, chip, searchTitle)

        historyTagList.addLast(searchTitle)
    }

    private fun bindsChipClickListener(chip: Chip, searchTitle: String) {
        chip.setOnCloseIconClickListener {
            viewModel.deleteSearchHistory(searchTitle)
            getViewBinding().cgSearchHistory.removeView(it)
            historyTagList.remove(searchTitle)
        }

        chip.setOnClickListener {
            getViewBinding().etSearch.setText(searchTitle)
        }
    }

    private fun addChipToGroup(shouldAddReverse: Boolean, chip: Chip, searchTitle: String) {
        if (shouldAddReverse.not()) {
            getViewBinding().cgSearchHistory.addView(chip)
        } else {
            if (checkTagList(searchTitle)) historyTagList.remove(searchTitle)

            getViewBinding().cgSearchHistory.addView(chip)
        }
    }

    private fun checkTagList(searchTitle: String): Boolean {
        historyTagList.forEachIndexed { index, s ->
            if (s == searchTitle) {
                getViewBinding().cgSearchHistory.removeViewAt(index)
                return true
            }
        }

        return false
    }
}
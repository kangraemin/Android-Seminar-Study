package com.dohyun.baeminapp.ui.view.search.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.databinding.FragmentSearchResultBinding
import com.dohyun.baeminapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultFragment : BaseFragment<FragmentSearchResultBinding>(R.layout.fragment_search_result) {

    private val viewModel by activityViewModels<SearchResultViewModel>()

    private val resultAdapter = ResultAdapter()

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchResultBinding {
        return FragmentSearchResultBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        val msg : SearchResultFragmentArgs by navArgs()
        requireDataBinding().resultSearchBar.editSearch.setText(msg.inputValue)

        requireDataBinding().resultSearchBar.searchBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        viewModel.doSearch(msg.inputValue, null)

        observeData()
        initResultList()
    }

    private fun initResultList() {
        with(requireDataBinding().resultListView) {
            adapter = resultAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeData() {
        with(viewModel) {
            result.observe(viewLifecycleOwner) {
                println("$it")
                for (data in it.data) {
                    println("SearchResult ::: $data")
                    resultAdapter.addItems(data)
                }

            }
        }
    }

}
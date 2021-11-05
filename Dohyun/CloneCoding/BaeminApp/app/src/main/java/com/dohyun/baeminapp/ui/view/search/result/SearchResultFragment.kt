package com.dohyun.baeminapp.ui.view.search.result

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.databinding.FragmentSearchResultBinding
import com.dohyun.baeminapp.ui.base.BaseFragment
import com.dohyun.baeminapp.ui.utils.toVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultFragment : BaseFragment<FragmentSearchResultBinding>(R.layout.fragment_search_result) {

    private val viewModel by activityViewModels<SearchResultViewModel>()

    private val resultAdapter = ResultAdapter()
    private val msg : SearchResultFragmentArgs by navArgs()
    private var count = 1

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchResultBinding {
        return FragmentSearchResultBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        viewModel.checkUserState()
        requireDataBinding().resultSearchBar.editSearch.setText(msg.inputValue)

        requireDataBinding().resultSearchBar.searchBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        requireDataBinding().resultSearchBar.editSearch.setOnKeyListener { v, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                count = 1
                viewModel.doSearch(requireDataBinding().resultSearchBar.editSearch.text.toString(), count)

                true
            }
            false
        }

        viewModel.doSearch(msg.inputValue, count)

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
            userState.observe(viewLifecycleOwner) {
                if (it == 0) updateUserState()
            }

            progressVisible.observe(viewLifecycleOwner) {
                requireDataBinding().progressBar.visibility = it.toVisibility()
            }

            result.observe(viewLifecycleOwner) {
                for (data in it.data) {
                    resultAdapter.addItems(data)
                }

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireDataBinding().resultListView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = requireDataBinding().resultListView.layoutManager

                if (viewModel.progressVisible.value != true) {
                    val lastVisibleItem = (layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

                    if (layoutManager.itemCount <= lastVisibleItem + 5) {
                        count++
                        viewModel.doSearch(msg.inputValue, count)
                    }
                }
            }
        })
    }

}
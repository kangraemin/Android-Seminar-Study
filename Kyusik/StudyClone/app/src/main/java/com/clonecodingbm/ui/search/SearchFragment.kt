package com.clonecodingbm.ui.search

import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clonecodingbm.R
import com.clonecodingbm.data.local.recentsearch.RecentSearchEntity
import com.clonecodingbm.databinding.FragmentSearchBinding
import com.clonecodingbm.ui.base.BaseFragment
import com.jakewharton.rxbinding4.widget.textChanges
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private lateinit var searchAdapter: SearchAdapter
    private val viewModel by viewModels<SearchViewModel>()
//    private lateinit var viewModel: SearchViewModel

    override fun init() {
//        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        searchAdapter = SearchAdapter()
        binding.rvSearch.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.rvSearch.adapter = searchAdapter

        searchAdapter.setOnItemClickListener(object : SearchAdapter.OnItemClickListener {
            override fun onItemClick(v: View, recentSearchEntity: RecentSearchEntity, pos: Int) {
                actionSearch(recentSearchEntity.searchWord)
            }

            override fun deleteItem(query: String) {
                viewModel.deleteRecentSearch(query)
            }
        })

        viewModel.apply {
            getRecentSearches()
            recentSearches.observe(viewLifecycleOwner, {
                searchAdapter.setRecentSearches(it.reversed())
                if (it.isEmpty()) {
                    binding.llRecentSearchContainer.visibility = View.GONE
                } else {
                    binding.llRecentSearchContainer.visibility = View.VISIBLE
                }
            })
            compositeDisposable.add(
                binding.etSearchInput
                    .textChanges()
                    .subscribe({
                        if (it.isNullOrBlank()) {
                            binding.ivSearchCancel.visibility = View.GONE
                        } else {
                            binding.ivSearchCancel.visibility = View.VISIBLE
                        }
                    }, { it.printStackTrace() })
            )
        }

        binding.apply {
            ivSearchBack.setOnClickListener {
                findNavController().popBackStack()
            }
            ivSearchCancel.setOnClickListener {
                binding.etSearchInput.text = null
            }
            etSearchInput.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    actionSearch(etSearchInput.text.toString())
                    true
                } else {
                    false
                }
            }
            btRecentSearchDeleteAll.setOnClickListener {
                viewModel.deleteRecentSearchAll()
            }
        }
    }

    private fun actionSearch(searchWord: String) {
        val action = SearchFragmentDirections.actionSearchFragmentToSearchListFragment(searchWord)
        findNavController().navigate(action)
        binding.etSearchInput.text = null
    }

    companion object {
        private const val TAG = "SearchFragment"
    }
}
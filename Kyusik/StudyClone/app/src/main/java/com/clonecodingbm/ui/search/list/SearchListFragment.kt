package com.clonecodingbm.ui.search.list

import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.clonecodingbm.R
import com.clonecodingbm.data.remote.search.Restaurant
import com.clonecodingbm.databinding.FragmentSearchListBinding
import com.clonecodingbm.ui.base.BaseFragment
import com.jakewharton.rxbinding4.widget.textChanges
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchListFragment : BaseFragment<FragmentSearchListBinding>(R.layout.fragment_search_list) {
    private val args: SearchListFragmentArgs by navArgs()
    private lateinit var searchListAdapter: SearchListAdapter
    private val viewModel by viewModels<SearchListViewModel>()
//    private lateinit var viewModel: SearchListViewModel

    override fun init() {
//        viewModel = ViewModelProvider(this)[SearchListViewModel::class.java]
        val query = args.query
        binding.etSearchInput.setText(query)

        searchListAdapter = SearchListAdapter()
        binding.rvSearchList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSearchList.adapter = searchListAdapter

        searchListAdapter.setOnItemClickListener(object : SearchListAdapter.OnItemClickListener {
            override fun onItemClick(v: View, restaurant: Restaurant, pos: Int) {
                showToast(restaurant.name)
            }
        })

        viewModel.apply {
            doSearchRequest(query, 1)
            isLoading.observe(viewLifecycleOwner, { isLoading ->
                if (isLoading) {
                    showLoading(true, binding.pbLoading)
                } else {
                    showLoading(false, binding.pbLoading)
                }
            })
            restaurants.observe(viewLifecycleOwner, {
                val list = it.restaurants
                searchListAdapter.setRestaurants(list)
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
                etSearchInput.text = null
                showKeyboard(etSearchInput)
            }
            etSearchInput.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    rvSearchList.removeAllViewsInLayout()
                    viewModel.doSearchRequest(etSearchInput.text.toString(), 1)
                    hideKeyboard(etSearchInput)
                    true
                } else {
                    false
                }
            }
        }
    }

    companion object {
        private const val TAG = "SearchListFragment"
    }

}
package com.clonecodingbm.ui.search.list

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.clonecodingbm.R
import com.clonecodingbm.databinding.FragmentSearchListBinding
import com.clonecodingbm.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchListFragment : BaseFragment<FragmentSearchListBinding>(R.layout.fragment_search_list) {
    private val args: SearchListFragmentArgs by navArgs()
    private lateinit var viewModel: SearchListViewModel

    override fun init() {
        viewModel = ViewModelProvider(this)[SearchListViewModel::class.java]
        val query = args.query
        binding.text.text = query
        viewModel.doSearchRequest(query, 1)
        viewModel.restaurants.observe(viewLifecycleOwner, {
            binding.text.text = it.restaurants.toString()
        })
    }

    companion object {
        private const val TAG = "SearchListFragment"
    }

}
package com.clonecodingbm.ui.search.list

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.clonecodingbm.R
import com.clonecodingbm.ui.base.BaseFragment
import com.clonecodingbm.databinding.FragmentSearchListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchListFragment : BaseFragment<FragmentSearchListBinding>(R.layout.fragment_search_list) {
    private val args: SearchListFragmentArgs by navArgs()
    private lateinit var viewModel: SearchListViewModel

    override fun init() {
        viewModel = ViewModelProvider(this)[SearchListViewModel::class.java]
        binding.text.text = args.query
    }

    companion object {
        private const val TAG = "SearchListFragment"
    }

}
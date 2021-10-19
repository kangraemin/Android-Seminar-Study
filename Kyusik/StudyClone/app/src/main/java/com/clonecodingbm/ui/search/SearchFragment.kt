package com.clonecodingbm.ui.search

import androidx.lifecycle.ViewModelProvider
import com.clonecodingbm.R
import com.clonecodingbm.databinding.FragmentSearchBinding
import com.clonecodingbm.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private lateinit var viewModel: SearchViewModel

    override fun init() {
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
    }

    companion object {
        private const val TAG = "SearchFragment"
    }
}
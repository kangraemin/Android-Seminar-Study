package com.example.deliveryclonecoding.ui.search

import android.util.Log
import androidx.fragment.app.viewModels
import com.example.deliveryclonecoding.R
import com.example.deliveryclonecoding.databinding.FragmentSearchBinding
import com.example.deliveryclonecoding.ui.base.BaseViewModelFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : BaseViewModelFragment<FragmentSearchBinding, SearchViewModel>() {
    override val layoutRes: Int
        get() = R.layout.fragment_search

    private val searchViewModel: SearchViewModel by viewModels()

    override fun createViewModel(): SearchViewModel {
        return searchViewModel.apply {
            binding.vm = searchViewModel
            binding.lifecycleOwner = this@SearchFragment
        }
    }
}
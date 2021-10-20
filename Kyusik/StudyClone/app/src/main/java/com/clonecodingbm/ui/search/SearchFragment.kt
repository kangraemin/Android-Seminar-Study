package com.clonecodingbm.ui.search

import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.clonecodingbm.R
import com.clonecodingbm.databinding.FragmentSearchBinding
import com.clonecodingbm.ui.base.BaseFragment
import com.jakewharton.rxbinding4.widget.textChanges
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private lateinit var viewModel: SearchViewModel

    override fun init() {
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        viewModel.apply {
            getRecentSearches()
            recentSearches.observe(viewLifecycleOwner, {
                val list = it.reversed()
                binding.tvLog.text = list.toString()
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
                    val action = SearchFragmentDirections.actionSearchFragmentToSearchListFragment(
                        etSearchInput.text.toString()
                    )
                    findNavController().navigate(action)
                    binding.etSearchInput.text = null
                    true
                } else {
                    false
                }
            }
        }
    }

    companion object {
        private const val TAG = "SearchFragment"
    }
}
package com.dohyun.baeminapp.view.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.ToolbarUtil
import com.dohyun.baeminapp.base.BaseFragment
import com.dohyun.baeminapp.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        ToolbarUtil.initToolbar(activity, R.id.search_toolbar, this.requireView())
    }

}
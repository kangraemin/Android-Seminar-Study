package com.clonecodingbm.ui.home

import androidx.lifecycle.ViewModelProvider
import com.clonecodingbm.R
import com.clonecodingbm.databinding.FragmentHomeBinding
import com.clonecodingbm.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var viewModel: HomeViewModel

    override fun init() {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }
}
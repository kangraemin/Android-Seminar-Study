package com.dohyun.baeminapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.base.BaseFragment
import com.dohyun.baeminapp.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        //
    }

}
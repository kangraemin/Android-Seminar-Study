package com.dohyun.baeminapp.ui.view.dibs

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.ui.utils.ToolbarUtil
import com.dohyun.baeminapp.ui.base.BaseFragment
import com.dohyun.baeminapp.databinding.FragmentDibsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DibsFragment : BaseFragment<FragmentDibsBinding>(R.layout.fragment_dibs) {

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDibsBinding {
        return FragmentDibsBinding.inflate(inflater, container, false).apply {
           lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        ToolbarUtil.initToolbar(activity, R.id.dibs_toolbar, this.requireView())
    }

}
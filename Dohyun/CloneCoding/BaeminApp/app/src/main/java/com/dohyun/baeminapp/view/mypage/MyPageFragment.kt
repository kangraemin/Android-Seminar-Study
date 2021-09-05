package com.dohyun.baeminapp.view.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.ToolbarUtil
import com.dohyun.baeminapp.base.BaseFragment
import com.dohyun.baeminapp.databinding.FragmentMyPageBinding

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyPageBinding {
        return FragmentMyPageBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        ToolbarUtil.initToolbar(activity, R.id.mypage_toolbar, this.requireView())
    }
}
package com.terry.delivery.features.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.terry.delivery.R
import com.terry.delivery.base.BaseFragment
import com.terry.delivery.databinding.FragmentMyPageBinding
import com.terry.delivery.util.SnackbarUtil

/*
 * Created by Taehyung Kim on 2021-08-29
 */
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
        initToolbar()

        getViewBinding().tvUserName.text = "로그인해주세요"
    }

    private fun bindViews() {
        getViewBinding().constraintLayoutLoginContainer.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_loginFragment)
        }
    }

    private fun initToolbar() {
        getViewBinding().tbMyPage.setupWithNavController(findNavController())
        getViewBinding().tbMyPage.title = ""
    }

}
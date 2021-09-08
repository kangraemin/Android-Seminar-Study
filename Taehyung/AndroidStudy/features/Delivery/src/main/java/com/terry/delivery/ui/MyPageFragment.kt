package com.terry.delivery.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
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

        val binding = binding ?: run {
            SnackbarUtil.showErrorMessage(view, "Error Occur !")
            return
        }

        bindViews(binding)
        initToolbar(binding)

        // TODO: 2021-09-07 REMOVE
        binding.userNameTextView.text = "로그인해주세요"
    }

    private fun bindViews(binding: FragmentMyPageBinding) {
        binding.loginContainerLayout.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_loginFragment)
        }
    }

    private fun initToolbar(binding: FragmentMyPageBinding) {
        binding.myPageToolbar.setupWithNavController(findNavController())
        binding.myPageToolbar.title = ""
    }

}
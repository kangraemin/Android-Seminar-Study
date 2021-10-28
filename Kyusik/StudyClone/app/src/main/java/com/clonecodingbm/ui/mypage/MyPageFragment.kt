package com.clonecodingbm.ui.mypage

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.clonecodingbm.R
import com.clonecodingbm.databinding.FragmentMyPageBinding
import com.clonecodingbm.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private val viewModel by viewModels<MyPageViewModel>()

    override fun init() {
        viewModel.apply {
            isLogin()

            isLoading.observe(viewLifecycleOwner, { isLoading ->
                if (isLoading) {
                    showLoading(true, binding.pbLoading)
                } else {
                    showLoading(false, binding.pbLoading)
                }
            })

            loginId.observe(viewLifecycleOwner, { loginId ->
                if (!loginId.isNullOrBlank()) {
                    checkToken()
                }
            })

            checkToken.observe(viewLifecycleOwner, {
                if (it) {
                    binding.tvNameLogin.text = "rank + ${viewModel.loginId.value}"
                    binding.clMyPageRank.visibility = View.VISIBLE
                } else {
                    binding.tvNameLogin.text = "로그인해주세요"
                    binding.clMyPageRank.visibility = View.GONE
                }
            })
        }

        binding.clMyPageLogin.setOnClickListener{
            if (binding.clMyPageRank.visibility == View.VISIBLE) {
                val action = MyPageFragmentDirections.actionMyPageFragmentToMyInfoFragment(viewModel.loginId.value.toString())
                findNavController().navigate(action)
            } else {
                findNavController().navigate(R.id.action_myPageFragment_to_loginFragment)
            }
        }
    }

    companion object {
        private const val TAG = "MyPageFragment"
    }
}
package com.clonecodingbm.ui.mypage.myinfo

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.clonecodingbm.R
import com.clonecodingbm.databinding.FragmentMyInfoBinding
import com.clonecodingbm.ui.base.BaseFragment
import com.clonecodingbm.ui.search.list.SearchListFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyInfoFragment : BaseFragment<FragmentMyInfoBinding>(R.layout.fragment_my_info) {
    private val args: MyInfoFragmentArgs by navArgs()
    private val viewModel by viewModels<MyInfoViewModel>()

    override fun init() {
        binding.etMyInfoId.setText(args.userName)

        viewModel.apply {
            isLoading.observe(viewLifecycleOwner, { isLoading ->
                if (isLoading) {
                    showLoading(true, binding.pbLoading)
                } else {
                    showLoading(false, binding.pbLoading)
                }
            })

            isLogout.observe(viewLifecycleOwner, { isLogout ->
                if (isLogout) {
                    findNavController().popBackStack()
                }
            })
        }

        binding.apply {
            ivMyInfoBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btMyInfoLogout.setOnClickListener {
                viewModel.logout()
            }
        }
    }
}
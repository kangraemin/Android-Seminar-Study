package com.dohyun.baeminapp.ui.view.logout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.databinding.FragmentLogoutBinding
import com.dohyun.baeminapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogoutFragment : BaseFragment<FragmentLogoutBinding>(R.layout.fragment_logout) {

    private val viewModel by activityViewModels<LogoutViewModel>()

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLogoutBinding {
        return FragmentLogoutBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        requireDataBinding().logoutBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        requireDataBinding().logoutBtn.setOnClickListener {
            viewModel.doLogout()
            observeData()
        }
    }

    private fun observeData() {
        with(viewModel) {
            logoutState.observe(viewLifecycleOwner) {
                if (it) {
                    findNavController().popBackStack()
                    showToast("로그아웃 하였습니다.")
                } else {
                    showToast("다시 시도해주세요.")
                }
            }
        }
    }

}
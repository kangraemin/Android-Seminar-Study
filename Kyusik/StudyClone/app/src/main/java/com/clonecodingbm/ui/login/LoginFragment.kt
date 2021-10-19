package com.clonecodingbm.ui.login

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.clonecodingbm.R
import com.clonecodingbm.ui.base.BaseFragment
import com.clonecodingbm.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private lateinit var viewModel: LoginViewModel

    override fun init() {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        viewModel.message.observe(viewLifecycleOwner, {
            showToast(it)
        })
        viewModel.loginResult.observe(viewLifecycleOwner, {
            if (it == true) {
                findNavController().popBackStack()
            }
        })
        viewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                showLoading(true, binding.pbLoading)
            } else {
                showLoading(false, binding.pbLoading)
            }
        })

        binding.btLoginClose.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btLoginLogin.setOnClickListener {
            viewModel.doLoginRequest(
                binding.etLoginIdInput.text.toString(),
                binding.etLoginPwInput.text.toString()
            )
        }
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}
package com.clonecodingbm.ui.login

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.clonecodingbm.R
import com.clonecodingbm.base.BaseFragment
import com.clonecodingbm.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private lateinit var viewModel: LoginViewModel

    override fun init() {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.message.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })
        viewModel.token.observe(viewLifecycleOwner, {
            binding.tvLoginRefresh.text = it.refresh
            binding.tvLoginAccess.text = it.access
            findNavController().popBackStack()
        })
        viewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                showToast("isLoading: true")
            } else {
                showToast("isLoading: false")
            }
        })

        binding.btLoginClose.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btLoginLogin.setOnClickListener {
            viewModel.doLoginRequest(binding.etLoginIdInput.text.toString(), binding.etLoginPwInput.text.toString())
        }
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}
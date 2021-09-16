package com.dohyun.baeminapp.ui.view.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.ui.base.BaseFragment
import com.dohyun.baeminapp.databinding.FragmentLoginBinding
import com.dohyun.baeminapp.BaminApplication

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory((requireActivity().application as BaminApplication).repository)
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {

        // 뒤로가기
        requireDataBinding().loginCloseBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        with(viewModel) {
            isIdEmpty.observe(viewLifecycleOwner, Observer {
                when (it) {
                    true -> setIdEmptyError()
                    false -> requireDataBinding().loginIdTextInputLayout.isErrorEnabled = false
                }
            })
            isPwEmpty.observe(viewLifecycleOwner, Observer {
                when(it) {
                    true -> setPwEmptyError()
                    false -> requireDataBinding().loginPwTextInputLayout.isErrorEnabled = false
                }
            })
            loginErrorMsg.observe(viewLifecycleOwner, Observer {
                showToast(getString(R.string.failure_login_msg))
            })
            successLogin.observe(viewLifecycleOwner, Observer {
                successLogin()
            })
        }
    }

    private fun setIdEmptyError() {
        requireDataBinding().loginIdTextInputLayout.error = getString(R.string.id_empty_error_msg)
    }

    private fun setPwEmptyError() {
        requireDataBinding().loginPwTextInputLayout.error = getString(R.string.pw_empty_error_msg)
    }

    private fun successLogin() {
        showToast(getString(R.string.success_login_msg))
        findNavController().popBackStack()
    }

}
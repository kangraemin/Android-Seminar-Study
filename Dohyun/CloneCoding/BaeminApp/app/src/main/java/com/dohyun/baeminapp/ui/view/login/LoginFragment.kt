package com.dohyun.baeminapp.ui.view.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.ui.base.BaseFragment
import com.dohyun.baeminapp.databinding.FragmentLoginBinding
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val viewModel by activityViewModels<LoginViewModel>()

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
        observeData()
    }

    private fun observeData() {
        with(viewModel) {
            loginState.observe(viewLifecycleOwner, Observer {
                when (it) {
                    true -> successLogin()
                    false -> showToast(getString(R.string.failure_login_msg))
                }
            })

            requireDataBinding().loginBtn.setOnClickListener {
                if (validation(requireDataBinding().loginIdTextInputLayout) &&
                    validation(requireDataBinding().loginPwTextInputLayout)) {

                    val id = requireDataBinding().loginEditId.text.toString()
                    val pw = requireDataBinding().loginEditPw.text.toString()

                    startLogin(id, pw)
                }
            }


        }
    }

    private fun validation(input: TextInputLayout): Boolean {
        if (input.editText!!.text.isNullOrEmpty()) {
            input.error = "입력을 확인해주세요!"
            return false
        }
        return true
    }

    private fun successLogin() {
        showToast(getString(R.string.success_login_msg))
        findNavController().popBackStack()
    }

}
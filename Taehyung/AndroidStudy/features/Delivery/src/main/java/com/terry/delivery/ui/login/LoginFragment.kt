package com.terry.delivery.ui.login

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.terry.delivery.R
import com.terry.delivery.base.BaseFragment
import com.terry.delivery.databinding.FragmentLoginBinding
import com.terry.delivery.util.KeyboardHelper
import com.terry.delivery.util.SnackbarUtil

/*
 * Created by Taehyung Kim on 2021-09-07
 */
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val loginViewModel by activityViewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = binding ?: run {
            SnackbarUtil.showErrorMessage(view, "Error Occur !!")
            return
        }

        bindViews(binding)
        bindObserver()
        initToolbar(binding)
    }

    private fun bindViews(binding: FragmentLoginBinding) {
        binding.loginButton.setOnClickListener {
            context?.let { context -> KeyboardHelper.hideKeyboard(context, binding.root) }

            // Check Null or Empty
            val isVerifyUserName = verifyUserName(binding)
            val isVerifyPassword = verifyPassword(binding)

            if (isVerifyUserName && isVerifyPassword) {
                loginViewModel.getAccessToken(
                    binding.idEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )

                return@setOnClickListener
            }

            SnackbarUtil.showErrorMessage(binding.root, "아이디, 비밀번호 확인 !")
        }
    }

    private fun bindObserver() {
        with(loginViewModel) {
            token.observe(viewLifecycleOwner) {
                binding?.let { binding ->
                    SnackbarUtil.showMessage(binding.root, "로그인 성공 !")
                }
            }

            loginError.observe(viewLifecycleOwner) {
                binding?.let { binding ->
                    SnackbarUtil.showErrorMessage(binding.root, "로그인 실패 !!")
                }
            }
        }
    }

    private fun initToolbar(binding: FragmentLoginBinding) {
        with(binding.loginToolbar) {
            setupWithNavController(findNavController())
            context?.let { context ->
                navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_close)
            }
            title = ""
        }
    }

    private fun verifyUserName(binding: FragmentLoginBinding) =
        binding.idEditText.text.isNullOrEmpty().not()

    private fun verifyPassword(binding: FragmentLoginBinding) =
        binding.passwordEditText.text.isNullOrEmpty().not()

}
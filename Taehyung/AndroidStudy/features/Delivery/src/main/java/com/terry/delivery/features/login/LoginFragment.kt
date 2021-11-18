package com.terry.delivery.features.login

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

        bindViews()
        bindObserver()
        initToolbar()
    }

    private fun bindViews() {
        getViewBinding().btLogin.setOnClickListener {
            context?.let { context -> KeyboardHelper.hideKeyboard(context, getViewBinding().root) }

            // Check Null or Empty
            val isVerifyUserName = verifyUserName()
            val isVerifyPassword = verifyPassword()

            if (isVerifyUserName && isVerifyPassword) {
                loginViewModel.login(
                    getViewBinding().etLoginId.text.toString(),
                    getViewBinding().etLoginPassword.text.toString()
                )

                return@setOnClickListener
            }

            SnackbarUtil.showErrorMessage(getViewBinding().root, "아이디, 비밀번호 확인 !")
        }
    }

    private fun bindObserver() {
        loginViewModel.loginResult.observe(viewLifecycleOwner) { isLoginSuccess ->
            if (isLoginSuccess) {
                getViewBinding().let { binding ->
                    SnackbarUtil.showMessage(binding.root, "로그인 성공 !")
                }
            } else {
                getViewBinding().let { binding ->
                    SnackbarUtil.showErrorMessage(binding.root, "로그인 실패 !!")
                }
            }
        }
    }

    private fun initToolbar() {
        with(getViewBinding().tbLogin) {
            setupWithNavController(findNavController())
            context?.let { context ->
                navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_close)
            }
            title = ""
        }
    }

    private fun verifyUserName() =
        getViewBinding().etLoginId.text.isNullOrEmpty().not()

    private fun verifyPassword() =
        getViewBinding().etLoginPassword.text.isNullOrEmpty().not()

}
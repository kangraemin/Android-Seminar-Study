package com.clonecodingbm.ui.login

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.clonecodingbm.R
import com.clonecodingbm.databinding.FragmentLoginBinding
import com.clonecodingbm.ui.base.BaseFragment
import com.jakewharton.rxbinding4.widget.textChanges
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val viewModel by viewModels<LoginViewModel>()

    override fun init() {
        val idWatcher = binding.etLoginIdInput.textChanges()
        val pwWatcher = binding.etLoginPwInput.textChanges()
        compositeDisposable
            .add(
                Observable
                    .combineLatest(
                        idWatcher,
                        pwWatcher,
                        BiFunction { idResult: CharSequence, pwResult: CharSequence ->
                            return@BiFunction idResult.isBlank() || pwResult.isBlank()
                        }
                    )
                    .subscribe({ blank ->
                        binding.btLoginLogin.isEnabled = !blank
                    }, { it.printStackTrace() })
            )

        viewModel.apply {
            message.observe(viewLifecycleOwner, {
                showToast(it)
            })
            loginResult.observe(viewLifecycleOwner, {
                if (it) {
                    findNavController().popBackStack()
                }
            })
            isLoading.observe(viewLifecycleOwner, { isLoading ->
                if (isLoading) {
                    showLoading(true, binding.pbLoading)
                } else {
                    showLoading(false, binding.pbLoading)
                }
            })
        }

        binding.apply {
            btLoginClose.setOnClickListener {
                findNavController().popBackStack()
           }
            btLoginLogin.setOnClickListener {
                viewModel.doLoginRequest(
                    binding.etLoginIdInput.text.toString(),
                    binding.etLoginPwInput.text.toString()
                )
            }
        }
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}
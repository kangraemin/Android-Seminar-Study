package com.example.deliveryclonecoding.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.deliveryclonecoding.R
import com.example.deliveryclonecoding.databinding.FragmentLoginBinding
import com.example.deliveryclonecoding.ui.base.BaseFragment
import com.jakewharton.rxbinding3.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.clicks()
            .throttleFirst(1000, TimeUnit.MILLISECONDS)
            .subscribe({
                loginViewModel.login(
                    id = binding.etId.text.toString(),
                    password = binding.etPassword.text.toString()
                )
            }, { it.printStackTrace() })
            .addTo(compositeDisposable)

        loginViewModel.loginResult.observe(viewLifecycleOwner, { successToLogin ->
            Log.d("login result", "success to login : $successToLogin")
        })
    }
}
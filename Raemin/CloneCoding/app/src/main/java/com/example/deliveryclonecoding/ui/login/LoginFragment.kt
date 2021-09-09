package com.example.deliveryclonecoding.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.deliveryclonecoding.R
import com.example.deliveryclonecoding.data.RepositoryFactory
import com.example.deliveryclonecoding.databinding.FragmentLoginBinding
import com.example.deliveryclonecoding.ui.base.BaseFragment
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val loginViewModel by lazy {
        LoginViewModel(
            RepositoryFactory.getRepository(
                requireActivity().applicationContext
            )
        )
    }

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
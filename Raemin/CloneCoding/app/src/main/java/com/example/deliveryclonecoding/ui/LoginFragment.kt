package com.example.deliveryclonecoding.ui

import android.os.Bundle
import android.view.View
import com.example.deliveryclonecoding.R
import com.example.deliveryclonecoding.databinding.FragmentLoginBinding
import com.example.deliveryclonecoding.ui.base.BaseFragment
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Observable
            .combineLatest(
                binding.etId.textChanges(),
                binding.etPassword.textChanges(),
                { id: CharSequence, password: CharSequence -> id.isNotEmpty() && password.isNotEmpty() }
            )
            .subscribe({
                binding.btnLogin.isEnabled = it
            }, { it.printStackTrace() })
            .addTo(compositeDisposable)

        binding.btnLogin.clicks()
            .throttleFirst(1000, TimeUnit.MILLISECONDS)
            .subscribe({

            }, { it.printStackTrace() })
            .addTo(compositeDisposable)
    }
}
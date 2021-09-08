package com.example.deliveryclonecoding.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deliveryclonecoding.databinding.FragmentLoginBinding
import com.example.deliveryclonecoding.ui.base.BaseFragment
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

class LoginFragment : BaseFragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

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
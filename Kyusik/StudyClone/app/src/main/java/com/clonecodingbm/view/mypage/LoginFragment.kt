package com.clonecodingbm.view.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.clonecodingbm.R
import com.clonecodingbm.base.BaseFragment
import com.clonecodingbm.databinding.FragmentLoginBinding
import com.clonecodingbm.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private lateinit var viewModel: LoginViewModel

    override fun init() {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.message.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })
        viewModel.loginData.observe(viewLifecycleOwner, {
            refresh.text = it.refresh
            access.text = it.access
            findNavController().popBackStack()
        })
        viewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                showToast("isLoading: true")
            } else {
                showToast("isLoading: false")
            }
        })

        binding.closeBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.loginButton.setOnClickListener {
            viewModel.doLoginRequest(id_input.text.toString(), password_input.text.toString())
        }
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}
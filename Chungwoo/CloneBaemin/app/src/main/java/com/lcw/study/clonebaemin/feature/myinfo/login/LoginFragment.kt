package com.lcw.study.clonebaemin.feature.myinfo.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.lcw.study.clonebaemin.R
import com.lcw.study.clonebaemin.databinding.FragmentLoginBinding
import com.lcw.study.clonebaemin.feature.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = loginViewModel

        loginViewModel.success.observe(viewLifecycleOwner) {
            Log.d("LoginFragment","value : $it")
        }
    }
}
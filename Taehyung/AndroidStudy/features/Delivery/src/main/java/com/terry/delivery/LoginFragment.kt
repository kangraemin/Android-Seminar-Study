package com.terry.delivery

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.terry.delivery.base.BaseFragment
import com.terry.delivery.databinding.FragmentLoginBinding
import com.terry.delivery.util.SnackbarUtil

/*
 * Created by Taehyung Kim on 2021-09-07
 */
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = binding ?: run {
            SnackbarUtil.showErrorMessage(view, "Error Occur !!")
            return
        }

        initToolbar(binding)
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

}
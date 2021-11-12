package com.example.deliveryclonecoding.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.deliveryclonecoding.R
import com.example.deliveryclonecoding.databinding.FragmentCustomerBinding
import com.example.deliveryclonecoding.ui.base.BaseFragment

class CustomerFragment : BaseFragment<FragmentCustomerBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_customer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarSettings()
        binding.layoutLogin.setOnClickListener {
            findNavController().navigate(R.id.action_customerFragment_to_loginFragment)
        }
    }

    private fun setToolbarSettings() {
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.toolbar.tvTitle.text = "My배민"
    }
}
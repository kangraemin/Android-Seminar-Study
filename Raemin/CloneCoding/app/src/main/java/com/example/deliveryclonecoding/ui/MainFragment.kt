package com.example.deliveryclonecoding.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.deliveryclonecoding.R
import com.example.deliveryclonecoding.ui.base.BaseFragment
import com.example.deliveryclonecoding.databinding.FragmentMainBinding


class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_main)
    }
}
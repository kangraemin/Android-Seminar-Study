package com.example.deliveryclonecoding.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.deliveryclonecoding.R
import com.example.deliveryclonecoding.base.BaseFragment
import com.example.deliveryclonecoding.databinding.FragmentMainBinding
import com.example.deliveryclonecoding.databinding.FragmentSplashBinding


class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_main)
    }
}
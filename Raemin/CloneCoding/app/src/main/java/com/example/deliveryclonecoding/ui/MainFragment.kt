package com.example.deliveryclonecoding.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.deliveryclonecoding.R
import com.example.deliveryclonecoding.databinding.FragmentMainBinding
import com.example.deliveryclonecoding.ui.base.BaseFragment


class MainFragment : BaseFragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        binding.layoutBottomNav.layoutSearch.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
        }
        binding.layoutBottomNav.layoutFavorite.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoriteFragment)
        }
        binding.layoutBottomNav.layoutOrderHistory.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_orderHistoryFragment)
        }
        binding.layoutBottomNav.layoutCustomer.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_customerFragment)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
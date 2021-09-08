package com.lcw.study.clonebaemin.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lcw.study.clonebaemin.R
import com.lcw.study.clonebaemin.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }


    private fun init() {
        binding.bottomMenu.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.search_tab -> {
                        findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
                    }
                    R.id.favorite_tab -> {
                        findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
                    }
                    R.id.home_tab -> {

                    }
                    R.id.orderlist_tab -> {
                        findNavController().navigate(R.id.action_homeFragment_to_orderListFragment)
                    }
                    R.id.myinfo_tab -> {
                        findNavController().navigate(R.id.action_homeFragment_to_myifoFragment)
                    }
                }
                true

            }

        }
    }

}
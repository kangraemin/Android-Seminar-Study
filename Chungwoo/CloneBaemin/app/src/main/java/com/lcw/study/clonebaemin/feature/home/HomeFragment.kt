package com.lcw.study.clonebaemin.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lcw.study.clonebaemin.R
import com.lcw.study.clonebaemin.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
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
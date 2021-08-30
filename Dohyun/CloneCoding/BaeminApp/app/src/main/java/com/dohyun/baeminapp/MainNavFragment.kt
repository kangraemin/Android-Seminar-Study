package com.dohyun.baeminapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dohyun.baeminapp.ui.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainNavFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_nav, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.main_bottom_nav_view)
        val fragmentTransaction = childFragmentManager.beginTransaction()
        val homeFragment = HomeFragment()
        fragmentTransaction.add(R.id.main_nav_frameLayout, homeFragment).commitAllowingStateLoss()
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.nav_search -> {
                    findNavController().navigate(R.id.action_to_searchFragment)
                }
                R.id.nav_dibs -> {
                    findNavController().navigate(R.id.action_to_dibsFragment)
                }
                R.id.nav_orders -> {
                    findNavController().navigate(R.id.action_to_ordersFragment)
                }
                R.id.nav_mypage -> {
                    findNavController().navigate(R.id.action_to_myPageFragment)
                }
            }
            true
        }
    }

}
package com.clonecodingbm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.ImageViewCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_nav.view.*

class NavFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.searchFragment -> {
                    findNavController().navigate(R.id.action_navFragment_to_searchFragment)
                }
                R.id.favoriteFragment -> {
                    findNavController().navigate(R.id.action_navFragment_to_favoriteFragment)
                }
                R.id.orderListFragment -> {
                    findNavController().navigate(R.id.action_navFragment_to_orderListFragment)
                }
                R.id.myPageFragment -> {
                    findNavController().navigate(R.id.action_navFragment_to_myPageFragment)
                }
            }
            true
        }
    }
}
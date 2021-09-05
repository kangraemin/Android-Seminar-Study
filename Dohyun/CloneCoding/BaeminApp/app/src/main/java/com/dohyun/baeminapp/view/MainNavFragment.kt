package com.dohyun.baeminapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.base.BaseFragment
import com.dohyun.baeminapp.databinding.FragmentMainNavBinding
import com.dohyun.baeminapp.view.home.HomeFragment

class MainNavFragment : BaseFragment<FragmentMainNavBinding>(R.layout.fragment_main_nav) {

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainNavBinding {
        return FragmentMainNavBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        //처음 홈 화면 설정
        val fragmentTransaction = childFragmentManager.beginTransaction()
        val homeFragment = HomeFragment()
        fragmentTransaction.add(R.id.main_nav_frameLayout, homeFragment).commitAllowingStateLoss()

        requireDataBinding().mainBottomNavView.setOnItemSelectedListener {
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
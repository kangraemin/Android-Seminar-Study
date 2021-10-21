package com.dohyun.baeminapp.ui.view.mainnav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.ui.base.BaseFragment
import com.dohyun.baeminapp.databinding.FragmentMainNavBinding
import com.dohyun.baeminapp.ui.view.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainNavFragment : BaseFragment<FragmentMainNavBinding>(R.layout.fragment_main_nav) {

    private val viewModel by activityViewModels<MainNavViewModel>()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.checkUserState()
        observeData()
    }

    private fun observeData() {
        with(viewModel) {
            logoutState.observe(viewLifecycleOwner) {
                if (it) {
                    showToast("다시 로그인이 필요합니다")
                }
            }

        }
    }

}
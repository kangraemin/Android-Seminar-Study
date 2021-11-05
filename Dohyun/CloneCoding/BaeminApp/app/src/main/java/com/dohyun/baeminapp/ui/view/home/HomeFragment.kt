package com.dohyun.baeminapp.ui.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.ui.base.BaseFragment
import com.dohyun.baeminapp.databinding.FragmentHomeBinding
import com.dohyun.baeminapp.ui.view.delivery.DeliveryFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        initViewPager()

        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(bottomSheetView)

        requireDataBinding().homeLocationTv.setOnClickListener {
            bottomSheetDialog.show()
        }
    }

    private fun initViewPager() {
        val pagerAdapter = PagerFragmentStateAdapter(this)
        pagerAdapter.addFragment(DeliveryFragment())
        pagerAdapter.addFragment(DeliveryFragment())
        pagerAdapter.addFragment(DeliveryFragment())
        pagerAdapter.addFragment(DeliveryFragment())
        pagerAdapter.addFragment(DeliveryFragment())

        requireDataBinding().homeViewpager.adapter = pagerAdapter
        requireDataBinding().homeViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                println("HomeFragment :: $position")
            }
        })
    }
}
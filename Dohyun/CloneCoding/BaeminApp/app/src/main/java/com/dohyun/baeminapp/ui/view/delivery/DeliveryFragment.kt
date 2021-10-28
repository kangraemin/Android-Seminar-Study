package com.dohyun.baeminapp.ui.view.delivery

import android.app.ActionBar
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.databinding.FragmentDeliveryBinding
import com.dohyun.baeminapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalStateException

@AndroidEntryPoint
class DeliveryFragment : BaseFragment<FragmentDeliveryBinding>(R.layout.fragment_delivery) {

    private val viewModel by activityViewModels<DeliveryViewModel>()
    private val foodAdapter = FoodAdapter()
    private var currentPosition = Int.MAX_VALUE / 2
    private var myHandler = MyHandler()
    private val intervalTime = 1500.toLong()

    override fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDeliveryBinding {
        return FragmentDeliveryBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        viewModel.initMenu()
        initBanner()
        observeData()
        initFoodList()
    }

    private fun initFoodList() {
        with(requireDataBinding().foodTypeList) {
            adapter = foodAdapter
            layoutManager = GridLayoutManager(requireContext(), 5)
        }
    }

    private fun observeData() {
        with(viewModel) {
            foodData.observe(viewLifecycleOwner) { data ->
                foodAdapter.addItems(data)
            }
        }
    }

    private fun initBanner() {
        with(requireDataBinding().bannerList) {
            adapter = ViewPagerAdapter(getBannerList())
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            setCurrentItem(currentPosition, false)

            apply {
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        requireDataBinding().currentBannerTv.text = "${(position % 3) + 1}"
                    }

                    override fun onPageScrollStateChanged(state: Int) {
                        super.onPageScrollStateChanged(state)
                        when (state) {
                            ViewPager2.SCROLL_STATE_IDLE -> autoScrollStart(intervalTime)
                            ViewPager2.SCROLL_STATE_DRAGGING -> autoScrollStop()
                        }
                    }
                })
            }
        }

    }

    private fun autoScrollStart(intervalTime: Long) {
        myHandler.removeMessages(0)
        myHandler.sendEmptyMessageDelayed(0, intervalTime)
    }

    private fun autoScrollStop() {
        myHandler.removeMessages(0)
    }

    private inner class MyHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            if (msg.what == 0) {
                requireDataBinding().bannerList.setCurrentItem(++currentPosition, true)
                autoScrollStart(intervalTime)
            }
        }
    }

    private fun getBannerList(): ArrayList<Int> {
        return arrayListOf<Int>(R.drawable.demo1, R.drawable.demo2, R.drawable.demo3)
    }

    override fun onResume() {
        super.onResume()
        autoScrollStart(intervalTime)
    }

    override fun onPause() {
        super.onPause()
        autoScrollStop()
    }

}
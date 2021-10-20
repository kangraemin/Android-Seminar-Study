package android.anjahyun.study.view.my

import android.anjahyun.study.R
import android.anjahyun.study.base.BaseFragment
import android.anjahyun.study.databinding.FragmentMyBinding
import android.anjahyun.study.util.visibleStatus
import android.anjahyun.study.viewmodel.MyViewModel
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFragment: BaseFragment<FragmentMyBinding>(FragmentMyBinding::inflate), View.OnClickListener {

    private val viewModel: MyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isMember()

        binding.layoutMember.setOnClickListener(this)
        binding.layoutPoint.setOnClickListener(this)
        binding.layoutCoupon.setOnClickListener(this)
        binding.layoutGift.setOnClickListener(this)
        binding.layoutFav.setOnClickListener(this)
        binding.layoutOrderlist.setOnClickListener(this)
        binding.layoutReview.setOnClickListener(this)
        binding.layoutGreen.setOnClickListener(this)
        binding.layoutPay.setOnClickListener(this)
        binding.layoutFamily.setOnClickListener(this)
        binding.layoutNotice.setOnClickListener(this)
        binding.layoutConnect.setOnClickListener(this)
        binding.layoutEvent.setOnClickListener(this)
        binding.layoutCs.setOnClickListener(this)
        binding.layoutSetting.setOnClickListener(this)
        binding.layoutPolicy.setOnClickListener(this)

        binding.tvVersion.text = "${resources.getString(R.string.my19)} 1.1.0"

        viewModel.isMember.observe(viewLifecycleOwner, {
            binding.layoutMemberO.visibleStatus(it)
            binding.layoutMemberX.visibleStatus(!it)
        })
    }

    override fun onClick(v: View?) {
        when(v) {
            binding.layoutMember -> {
                findNavController().navigate(R.id.loginFragment)
            }
        }
    }

}
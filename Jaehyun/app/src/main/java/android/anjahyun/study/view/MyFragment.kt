package android.anjahyun.study.view

import android.anjahyun.study.R
import android.anjahyun.study.base.BaseFragment
import android.anjahyun.study.databinding.FragmentMyBinding
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController

class MyFragment: BaseFragment<FragmentMyBinding>(FragmentMyBinding::inflate), View.OnClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layoutMember.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            binding.layoutMember -> {
                findNavController().navigate(R.id.loginFragment)
            }
        }
    }

}
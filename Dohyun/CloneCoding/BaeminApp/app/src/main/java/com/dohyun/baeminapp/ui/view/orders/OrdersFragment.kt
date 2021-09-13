package com.dohyun.baeminapp.ui.view.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.ui.utils.ToolbarUtil
import com.dohyun.baeminapp.ui.base.BaseFragment
import com.dohyun.baeminapp.databinding.FragmentOrdersBinding

class OrdersFragment : BaseFragment<FragmentOrdersBinding>(R.layout.fragment_orders) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_orders, container, false)

        return view
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOrdersBinding {
        return FragmentOrdersBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        ToolbarUtil.initToolbar(activity, R.id.orders_toolbar, this.requireView())
    }
}
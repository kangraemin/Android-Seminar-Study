package com.terry.delivery.ui

import android.os.Bundle
import com.terry.delivery.base.BaseActivity
import com.terry.delivery.databinding.ActivityDeliveryMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeliveryMainActivity :
    BaseActivity<ActivityDeliveryMainBinding>(ActivityDeliveryMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}
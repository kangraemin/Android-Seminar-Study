package com.terry.androidstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.terry.androidstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindViews(binding)

        binding.deliveryModuleButton.performClick()
    }

    private fun bindViews(binding: ActivityMainBinding) {
        binding.testCodeModuleButton.setOnClickListener {
            startModuleActivity("com.terry.testcode.TestCodeMainActivity")
        }

        binding.deliveryModuleButton.setOnClickListener {
            startModuleActivity("com.terry.delivery.ui.DeliveryMainActivity")
        }
    }
}
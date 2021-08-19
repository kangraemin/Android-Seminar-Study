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
    }

    private fun bindViews(binding: ActivityMainBinding) {
        binding.testCodeModulebutton.setOnClickListener {
            startModuleActivity("com.terry.testcode.TestCodeMainActivity")
        }
    }
}
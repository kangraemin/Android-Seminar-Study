package com.terry.testcode

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.terry.testcode.databinding.ActivityTestCodeMainBinding
import com.terry.testcode.mvc.MvcMainActivity
import com.terry.testcode.mvp.MvpMainActivity
import com.terry.testcode.mvvm.MvvmMainActivity

class TestCodeMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTestCodeMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindViews(binding)
    }

    private fun bindViews(binding: ActivityTestCodeMainBinding) {
        binding.mvcActivityButton.setOnClickListener {
            startActivity(Intent(this, MvcMainActivity::class.java))
        }

        binding.mvpActivityButton.setOnClickListener {
            startActivity(Intent(this, MvpMainActivity::class.java))
        }

        binding.mvvmActivityButton.setOnClickListener {
            startActivity(Intent(this, MvvmMainActivity::class.java))
        }
    }
}
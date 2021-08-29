package com.example.deliveryclonecoding

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE
import android.text.style.AbsoluteSizeSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.deliveryclonecoding.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        setSplashTitle()
    }

    private fun setSplashTitle() {
        binding.tvSplashTitle.text =
            SpannableString(resources.getString(R.string.splash_title)).apply {
                setSpan(
                    AbsoluteSizeSpan(
                        resources.getDimension(R.dimen.splash_title_particle_size).toInt()
                    ), 2, 3, SPAN_INCLUSIVE_INCLUSIVE
                )
            }
    }
}
package com.example.deliveryclonecoding

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE
import android.text.style.AbsoluteSizeSpan
import androidx.databinding.DataBindingUtil
import com.example.deliveryclonecoding.base.BaseActivity
import com.example.deliveryclonecoding.databinding.ActivitySplashBinding
import io.reactivex.Completable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val mainIntent: Intent by lazy { Intent(this, MainActivity::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        setSplashTitle()
        startSplashTimer()
    }

    private fun startSplashTimer() {
        Completable
            .timer(2000, TimeUnit.MILLISECONDS)
            .subscribe({
                startActivity(mainIntent)
            }, { it.printStackTrace() })
            .addTo(compositeDisposable)
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
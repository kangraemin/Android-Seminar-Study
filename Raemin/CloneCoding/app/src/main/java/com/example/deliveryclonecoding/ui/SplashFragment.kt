package com.example.deliveryclonecoding.ui

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.deliveryclonecoding.R
import com.example.deliveryclonecoding.databinding.FragmentSplashBinding
import com.example.deliveryclonecoding.ui.base.BaseFragment
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

class SplashFragment : BaseFragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding by lazy { _binding!! }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSplashTitle()
        startSplashTimer()
    }

    private fun startSplashTimer() {
        Completable
            .timer(2000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }, { it.printStackTrace() })
            .addTo(compositeDisposable)
    }

    private fun setSplashTitle() {
        binding.tvSplashTitle.text =
            SpannableString(resources.getString(R.string.splash_title)).apply {
                setSpan(
                    AbsoluteSizeSpan(
                        resources.getDimension(R.dimen.splash_title_particle_size).toInt()
                    ), 2, 3, Spanned.SPAN_INCLUSIVE_INCLUSIVE
                )
            }
    }

}
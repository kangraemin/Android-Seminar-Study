package com.lcw.study.clonebaemin.feature.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lcw.study.clonebaemin.R
import java.util.*
import kotlin.concurrent.timer

class SplashFragment : Fragment() {
    private var timerTask: Timer? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        timerTask = timer(period = 1000, initialDelay = 3000) {
            activity?.runOnUiThread {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }
            timerTask?.cancel()
        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

}
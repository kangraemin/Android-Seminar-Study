package com.dohyun.baeminapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commitNow(true) {
            val mainFragment : Fragment = MainFragment()
            replace(R.id.main_content, mainFragment)
            setPrimaryNavigationFragment(mainFragment)
        }
    }
}
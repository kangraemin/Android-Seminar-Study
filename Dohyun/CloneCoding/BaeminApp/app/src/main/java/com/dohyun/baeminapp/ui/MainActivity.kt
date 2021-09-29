package com.dohyun.baeminapp.ui

import android.os.Bundle
import androidx.fragment.app.commitNow
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.ui.base.BaseActivity
import com.dohyun.baeminapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.commitNow(true) {
            val mainFragment = MainFragment()
            replace(R.id.main_content, mainFragment)
            setPrimaryNavigationFragment(mainFragment)
        }
    }
}
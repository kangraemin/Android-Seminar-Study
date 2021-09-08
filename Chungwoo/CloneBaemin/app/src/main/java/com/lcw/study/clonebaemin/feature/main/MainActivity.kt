package com.lcw.study.clonebaemin.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lcw.study.clonebaemin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun init() {
/*        binding.bottomMenu.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.search_tab -> {
                        changeFragment(searchFragment)
                    }
                    R.id.favorite_tab -> {
                        changeFragment(favoriteFragment)
                    }
                    R.id.home_tab -> {
                        changeFragment(homeFragment)
                    }
                    R.id.orderlist_tab -> {
                        changeFragment(orderListFragment)
                    }
                    R.id.myinfo_tab -> {
                        changeFragment(myifoFragment)
                    }
                }
                true

            }
            selectedItemId = R.id.home_tab

        }*/
    }


}
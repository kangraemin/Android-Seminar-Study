package com.lcw.study.clonebaemin.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lcw.study.clonebaemin.R
import com.lcw.study.clonebaemin.databinding.ActivityMainBinding
import com.lcw.study.clonebaemin.feature.favorite.FavoriteFragment
import com.lcw.study.clonebaemin.feature.home.HomeFragment
import com.lcw.study.clonebaemin.feature.myinfo.MyifoFragment
import com.lcw.study.clonebaemin.feature.orderlist.OrderListFragment
import com.lcw.study.clonebaemin.feature.search.SearchFragment

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!


    private val searchFragment by lazy { SearchFragment() }
    private val favoriteFragment by lazy { FavoriteFragment() }
    private val homeFragment by lazy { HomeFragment() }
    private val orderListFragment by lazy { OrderListFragment() }
    private val myifoFragment by lazy { MyifoFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.bottomMenu.run {
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

        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

}
package android.anjahyun.study

import android.anjahyun.study.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
                R.id.splashFragment -> findViewById<ConstraintLayout>(R.id.layout_bottom).isVisible = false
                else -> findViewById<ConstraintLayout>(R.id.layout_bottom).isVisible = true
            }
        }

        findViewById<ConstraintLayout>(R.id.btn_search).setOnClickListener {
            navController.navigate(R.id.searchFragment)
        }
        findViewById<ConstraintLayout>(R.id.btn_fav).setOnClickListener {
            navController.navigate(R.id.favFragment)
        }
        findViewById<ImageView>(R.id.btn_main).setOnClickListener {
            navController.navigate(R.id.mainFragment)
        }
        findViewById<ConstraintLayout>(R.id.btn_orderlist).setOnClickListener {
            navController.navigate(R.id.orderListFragment)
        }
        findViewById<ConstraintLayout>(R.id.btn_my).setOnClickListener {
            navController.navigate(R.id.myFragment)
        }

    }
}
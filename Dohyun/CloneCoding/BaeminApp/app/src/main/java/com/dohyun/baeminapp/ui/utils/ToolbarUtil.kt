package com.dohyun.baeminapp.ui.utils

import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity

object ToolbarUtil {

    fun initToolbar(activity: FragmentActivity?, @IdRes id : Int, view: View) {
        val toolbar = view.findViewById<Toolbar>(id)
        val getActivity = activity as AppCompatActivity
        getActivity.setSupportActionBar(toolbar)
        getActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
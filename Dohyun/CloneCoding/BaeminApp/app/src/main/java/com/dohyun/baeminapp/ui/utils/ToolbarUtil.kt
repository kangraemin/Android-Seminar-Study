package com.dohyun.baeminapp.ui.utils

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity

object ToolbarUtil {

    fun initToolbar(activity: FragmentActivity?, id : Int, view: View) {
        val toolbar = view.findViewById<Toolbar>(id)
        val getActivity = activity as AppCompatActivity
        getActivity.setSupportActionBar(toolbar)
        getActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
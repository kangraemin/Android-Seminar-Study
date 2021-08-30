package com.dohyun.baeminapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.ToolbarUtil

class MyPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_page, container, false)
        ToolbarUtil.initToolbar(activity, R.id.mypage_toolbar, view)
        return view
    }
}
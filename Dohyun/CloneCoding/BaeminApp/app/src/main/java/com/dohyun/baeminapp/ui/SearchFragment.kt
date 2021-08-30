package com.dohyun.baeminapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.ToolbarUtil

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        ToolbarUtil.initToolbar(activity, R.id.search_toolbar, view)

        return view
    }

}
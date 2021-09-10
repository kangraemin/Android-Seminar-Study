package com.lcw.study.clonebaemin.feature.myinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.lcw.study.clonebaemin.R
import com.lcw.study.clonebaemin.databinding.FragmentMyifoBinding


class MyifoFragment : Fragment() {
    private lateinit var binding: FragmentMyifoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_myifo,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.llRequestLogin.setOnClickListener {
            findNavController().navigate(R.id.action_myifoFragment_to_loginFragment)
        }

    }


}
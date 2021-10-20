package com.example.deliveryclonecoding.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

abstract class BaseViewModelFragment<VDB : ViewDataBinding, VM : BaseViewModel>: BaseFragment<VDB>() {
    abstract fun createViewModel(): VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return super.onCreateView(inflater, container, savedInstanceState).apply {
            createViewModel()
        }
    }
}
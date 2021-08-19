package com.terry.testcode.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terry.testcode.ResultModel

/*
 * Created by Taehyung Kim on 2021-08-14
 */
class MvvmViewModelFactory(
    private val resultModel: ResultModel
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != MvvmViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }

        return MvvmViewModel(resultModel) as T
    }
}
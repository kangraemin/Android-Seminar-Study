package com.clonecodingbm.ui.home

import com.clonecodingbm.data.repository.home.HomeRepository
import com.clonecodingbm.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : BaseViewModel() {

}
package com.clonecodingbm.ui.search.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clonecodingbm.data.remote.search.SearchResponse
import com.clonecodingbm.data.repository.search.SearchRepository
import com.clonecodingbm.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchListViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : BaseViewModel() {
    private val _restaurants = MutableLiveData<SearchResponse>()
    val restaurants: LiveData<SearchResponse> get() = _restaurants

    fun doDetailRequest() {

    }
}
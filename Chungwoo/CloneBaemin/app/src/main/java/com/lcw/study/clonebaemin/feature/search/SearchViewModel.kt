package com.lcw.study.clonebaemin.feature.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lcw.study.clonebaemin.data.ApiService
import com.lcw.study.clonebaemin.data.search.RequestSearchData
import com.lcw.study.clonebaemin.data.search.SearchData
import com.lcw.study.clonebaemin.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val api: ApiService
) : BaseViewModel() {


}
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

    private val _success: MutableLiveData<Boolean> = MutableLiveData()
    val success: LiveData<Boolean> get() = _success

    private val _searchData: MutableLiveData<SearchData> = MutableLiveData()
    val searchData: LiveData<SearchData> get() = _searchData


    fun getSearch(apiKey:String,query: String, page: Int) {
        api.getSearch(apiKey,query ,page)
            .subscribeOn(Schedulers.io())  //상위스트림
            .subscribe({
                Log.d("SearchViewModel", "response success: $it ")
                _searchData.postValue(it)
            }, {
                Log.d("SearchViewModel", "response fail: $it ")
            })


    }
}
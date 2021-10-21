package com.lcw.study.clonebaemin.feature.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lcw.study.clonebaemin.data.ApiService
import com.lcw.study.clonebaemin.data.search.SearchData
import com.lcw.study.clonebaemin.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchListViewModel @Inject constructor(private val api: ApiService) : BaseViewModel() {

    private val _success: MutableLiveData<Boolean> = MutableLiveData()
    val success: LiveData<Boolean> get() = _success

     val _searchData: MutableLiveData<SearchData> = MutableLiveData()
    val searchData: LiveData<SearchData> get() = _searchData


    fun getSearch(apiKey: String, query: String, page: Int) {
        api.getSearch(apiKey, query, page)
            .subscribeOn(Schedulers.io())  //상위스트림
            .subscribe({
                Log.d("SearchListViewModel", "response success: ${it.restaurants} ")
                _searchData.postValue(it)
            }, {
                Log.d("SearchListViewModel", "response fail: $it ")
            })


    }
}
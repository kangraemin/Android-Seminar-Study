package com.terry.delivery.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-09-29
 */
@HiltViewModel
class SearchViewModel @Inject constructor(

) : ViewModel() {

    private val _searchRankData = MutableLiveData<List<SearchRankItem>>()
    val searchRankData: LiveData<List<SearchRankItem>>
        get() = _searchRankData

    fun initDebugRankData() {
        _searchRankData.postValue(
            listOf(
                SearchRankItem(1, "bbq", 0),
                SearchRankItem(2, "bhc", 0),
                SearchRankItem(3, "닭발", 0),
                SearchRankItem(4, "타코야끼", 0),
                SearchRankItem(5, "자담치킨", 0),
                SearchRankItem(6, "꼬치", 2),
                SearchRankItem(7, "역전", 0),
                SearchRankItem(8, "닭꼬치", 1),
                SearchRankItem(9, "햄버거", 0),
                SearchRankItem(10, "순대", 0)
            )
        )
    }

}
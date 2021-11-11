package com.terry.delivery.features.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.EmptyResultSetException
import com.terry.delivery.base.BaseViewModel
import com.terry.delivery.data.SearchRepository
import com.terry.delivery.data.remote.model.search.Ranking
import com.terry.delivery.entity.search.RestaurantDto
import com.terry.delivery.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.InputStream
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-09-29
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
) : BaseViewModel() {

    private val _searchRankData = SingleLiveEvent<Ranking>()
    val searchRankData: LiveData<Ranking>
        get() = _searchRankData

    private val _queryResult = SingleLiveEvent<List<RestaurantDto>>()
    val queryResult: LiveData<List<RestaurantDto>> = _queryResult

    private val _failMessage = SingleLiveEvent<String>()
    val failMessage: LiveData<String> = _failMessage

    val searchHistories = searchRepository.getSearchHistory(count = 10)

    fun initDebugRankData(rawData: InputStream) {
        searchRepository.getTop10RankedData(rawData.bufferedReader().use { it.readText() })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ ranking ->
                _searchRankData.value = ranking
            }, {
                it.printStackTrace()
            })
            .addTo(disposable)
    }

    fun searchItem(query: String) {
        searchRepository.searchItemQuery(query, 1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                if (result.isSuccess) {
                    val data = result.getOrNull() ?: return@subscribe

                    _queryResult.value = data.restaurantDto
                } else {
                    val error = result.exceptionOrNull()
                    Timber.w(error)
                    if (error != null && error is EmptyResultSetException) {
                        _failMessage.postValue("EmptyResultSetException !")
                    }
                }
            }
            .addTo(disposable)
    }

    fun saveSearchHistory(searchQuery: String) = viewModelScope.launch {
        searchRepository.saveSearchHistory(searchQuery)
    }

    fun deleteSearchHistoryAll() = viewModelScope.launch {
        searchRepository.deleteSearchHistoryAll()
    }

    fun deleteSearchHistory(title: String) = viewModelScope.launch {
        searchRepository.deleteSearchHistory(title)
    }
}
package com.terry.delivery.features.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.terry.delivery.base.BaseViewModel
import com.terry.delivery.data.SearchRepository
import com.terry.delivery.data.remote.model.search.Ranking
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import java.io.InputStream
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-09-29
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : BaseViewModel() {

    private val _searchRankData = MutableLiveData<Ranking>()
    val searchRankData: LiveData<Ranking>
        get() = _searchRankData

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

}
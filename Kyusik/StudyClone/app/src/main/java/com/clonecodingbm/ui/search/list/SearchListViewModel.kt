package com.clonecodingbm.ui.search.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clonecodingbm.data.remote.search.SearchResponse
import com.clonecodingbm.data.repository.search.SearchRepository
import com.clonecodingbm.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class SearchListViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : BaseViewModel() {
    private val _restaurants = MutableLiveData<SearchResponse>()
    val restaurants: LiveData<SearchResponse> get() = _restaurants

    fun doSearchRequest(query: String, page: Int) {
        compositeDisposable.add(
            searchRepository
                .searchAndSave(query, page)
                .doOnSubscribe { showProgress() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    hideProgress()
                    _restaurants.value = it
                }, {
                    hideProgress()
                    it.printStackTrace()
                })
        )
    }
}
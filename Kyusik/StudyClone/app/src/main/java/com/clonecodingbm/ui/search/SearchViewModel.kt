package com.clonecodingbm.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clonecodingbm.data.local.recentsearch.RecentSearchEntity
import com.clonecodingbm.data.repository.search.SearchRepository
import com.clonecodingbm.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : BaseViewModel() {
    private val _recentSearches = MutableLiveData<List<RecentSearchEntity>>()
    val recentSearches: LiveData<List<RecentSearchEntity>> get() = _recentSearches

    fun getRecentSearches() {
        compositeDisposable.add(
            searchRepository
                .getRecentSearches()
                .doOnSubscribe { showProgress() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    hideProgress()
                    _recentSearches.value = it
                }, {
                    hideProgress()
                    it.printStackTrace()
                })
        )
    }

    fun deleteRecentSearch(query: String) {
        compositeDisposable.add(
            searchRepository
                .deleteRecentSearch(query)
                .doOnSubscribe { showProgress() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    hideProgress()
                    _recentSearches.value = it
                },{
                    hideProgress()
                })
        )
    }

    fun deleteRecentSearchAll() {
        compositeDisposable.add(
            searchRepository
                .deleteRecentSearchAll().doOnSubscribe { showProgress() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    hideProgress()
                    _recentSearches.value = emptyList()
                },{
                    hideProgress()
                })
        )
    }
}
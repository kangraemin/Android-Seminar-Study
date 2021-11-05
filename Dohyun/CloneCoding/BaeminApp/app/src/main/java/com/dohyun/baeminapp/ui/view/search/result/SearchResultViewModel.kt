package com.dohyun.baeminapp.ui.view.search.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dohyun.baeminapp.data.entity.Restaurant
import com.dohyun.baeminapp.data.entity.Results
import com.dohyun.baeminapp.data.repository.search.SearchRepository
import com.dohyun.baeminapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val repository: SearchRepository
) : BaseViewModel() {

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible : LiveData<Boolean>
        get() = _progressVisible

    private val _result = MutableLiveData<Results>()
    val result : LiveData<Results>
        get() = _result

    private var disposable : CompositeDisposable? = CompositeDisposable()

    private val _userState = MutableLiveData<Int>(-1)
    val userState : LiveData<Int>
        get() = _userState

    fun doSearch(query: String, page: Int?) {
        _progressVisible.postValue(true)
        disposable?.add(
            repository.search(query, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    println("SearchResultViewModel result :: $it")
                    _result.postValue(it)
                    _progressVisible.postValue(false)
                }, {
                    println("SearchResultViewModel search error ${it.message}")
                })
        )
    }

    fun checkUserState() {
        repository.checkToken()
                .observeOn(Schedulers.io())
                .subscribe({
                    _userState.postValue(1)
                }, {
                    _userState.postValue(0)
                    println("SearchResultViewModel checkUserState error ${it.message}")
                })
    }

    fun updateUserState() {
        repository.updateToken()
                .observeOn(Schedulers.io())
                .subscribe({
                    _userState.postValue(1)
                }, {
                    println("SearchResultViewModel updateUserState error ${it.message}")
                })
    }
}
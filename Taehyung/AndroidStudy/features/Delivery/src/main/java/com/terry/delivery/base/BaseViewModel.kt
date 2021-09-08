package com.terry.delivery.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/*
 * Created by Taehyung Kim on 2021-09-08
 */
abstract class BaseViewModel : ViewModel() {

    protected val disposable = CompositeDisposable()

    protected val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

}
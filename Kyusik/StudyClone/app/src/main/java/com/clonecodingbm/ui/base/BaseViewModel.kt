package com.clonecodingbm.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun showToast(msg: String) {
        _message.postValue(msg)
    }

    protected fun showProgress() {
        _isLoading.postValue(true)
    }

    protected fun hideProgress() {
        _isLoading.value = false
    }
}
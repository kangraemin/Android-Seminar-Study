package com.terry.delivery.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/*
 * Created by Taehyung Kim on 2021-09-08
 */
abstract class BaseViewModel : ViewModel() {

    protected val disposable = CompositeDisposable()

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

}
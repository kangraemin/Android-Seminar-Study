package com.dohyun.baeminapp.ui.view.logout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dohyun.baeminapp.data.repository.logout.LogoutRepository
import com.dohyun.baeminapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(
    private val repository: LogoutRepository
): BaseViewModel() {

    private val _logoutState = MutableLiveData<Boolean>()
    val logoutState : LiveData<Boolean> = _logoutState

    private var disposable: CompositeDisposable? = CompositeDisposable()

    fun doLogout() {
        println("logout btn click!")
        repository.logout()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                println("logout success")
                _logoutState.value = true
            }, {
                _logoutState.value = false
                println("logout error ${it.message}")
            })
    }
}
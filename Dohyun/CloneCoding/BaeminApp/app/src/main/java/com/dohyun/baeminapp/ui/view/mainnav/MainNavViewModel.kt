package com.dohyun.baeminapp.ui.view.mainnav

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dohyun.baeminapp.data.entity.Token
import com.dohyun.baeminapp.data.repository.mainnav.MainNavRepository
import com.dohyun.baeminapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainNavViewModel @Inject constructor(
    private val repository: MainNavRepository
): BaseViewModel() {

    private val _userState = MutableLiveData<Int>(-1)
    val userState: LiveData<Int>
        get() = _userState

    fun checkTokens() {
        repository.verifyToken()
                .observeOn(Schedulers.io())
                .subscribe({
                    _userState.postValue(1)
                }, {
                    if (it.message == "Query returned empty result set: SELECT access FROM Token") {
                        println("MainNavViewModel checkTokens login is not yet")
                    } else {
                        _userState.postValue(0)
                        println("MainNavViewModel checkTokens ${it.message}")
                    }
                })
    }

    fun refreshAccessTokens() {
        if (_userState.value == 0) {
            repository.updateToken()
                    .observeOn(Schedulers.io())
                    .subscribe({
                        _userState.postValue(1)
                    }, {
                        println("MainNavViewModel refreshTokens fail ${it.message}")
                    })
        }
    }

}
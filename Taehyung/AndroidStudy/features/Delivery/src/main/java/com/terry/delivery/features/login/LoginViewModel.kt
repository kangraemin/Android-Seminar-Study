package com.terry.delivery.features.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.terry.delivery.base.BaseViewModel
import com.terry.delivery.data.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-09-08
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : BaseViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean>
        get() = _loginResult

    fun login(id: String, password: String) {
        loginRepository
            .login(id, password)
            .doOnError {
                _loginResult.postValue(false)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _loginResult.postValue(true)
            }, {
                it.printStackTrace()
            })
            .addTo(disposable)
    }
}
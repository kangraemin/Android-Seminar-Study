package com.lcw.study.clonebaemin.feature.myinfo.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.lcw.study.clonebaemin.data.login.LoginDatasourceImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(private val loginDatasourceImpl: LoginDatasourceImpl ): ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    fun requestLogin(id: String, password: String) {
        loginDatasourceImpl
            .login(id, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       Log.d("LoginViewmodel","response:$it ")
            }, {
                Log.d("LoginViewmodel","response:$it ")
            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
package com.lcw.study.clonebaemin.feature.myinfo.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lcw.study.clonebaemin.data.RetrofitClient
import com.lcw.study.clonebaemin.data.login.LoginDatasourceImpl
import com.lcw.study.clonebaemin.data.login.RequestLoginInfoData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()


    private val _success: MutableLiveData<Boolean> = MutableLiveData()
    val success: LiveData<Boolean> get() = _success


    fun requestLogin() {
        RetrofitClient.getService().requestLogin(RequestLoginInfoData("delivery", "dev_baemin"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _success.postValue(true)
                Log.d("LoginViewmodel", "response success: $it ")
            }, {
                Log.d("LoginViewmodel", "response fail: $it ")
            })

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
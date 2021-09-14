package com.lcw.study.clonebaemin.feature.myinfo.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lcw.study.clonebaemin.data.RetrofitClient
import com.lcw.study.clonebaemin.data.login.RequestLoginInfoData
import com.lcw.study.clonebaemin.feature.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel : BaseViewModel() {

    private val _success: MutableLiveData<Boolean> = MutableLiveData()
    val success: LiveData<Boolean> get() = _success


    fun requestLogin(userName: String, password: String) {
        //RetrofitClient.getService().requestLogin(RequestLoginInfoData("delivery", "dev_baemin"))
        RetrofitClient.getService()
            .requestLogin(RequestLoginInfoData(username = userName, password = password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _success.postValue(true)
                Log.d("LoginViewmodel", "response success: $it ")
            }, {
                Log.d("LoginViewmodel", "response fail: $it ")
            })

    }
}
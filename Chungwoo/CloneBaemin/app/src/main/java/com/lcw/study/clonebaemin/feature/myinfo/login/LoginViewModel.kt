package com.lcw.study.clonebaemin.feature.myinfo.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lcw.study.clonebaemin.data.ApiService
import com.lcw.study.clonebaemin.data.login.RequestLoginInfoData
import com.lcw.study.clonebaemin.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val api: ApiService
) : BaseViewModel() {

    private val _success: MutableLiveData<Boolean> = MutableLiveData()
    val success: LiveData<Boolean> get() = _success


    /*  fun requestLogin(userName: String, password: String) {
          RetrofitClient.getService()
              .requestLogin(RequestLoginInfoData(username = userName, password = password))
              .subscribeOn(Schedulers.io())  //상위스트림
              //.observeOn(AndroidSchedulers.mainThread()) //하위스트림
              .subscribe({
                  _success.postValue(true)
                  Log.d("LoginViewmodel", "response success: $it ")
              }, {
                  Log.d("LoginViewmodel", "response fail: $it ")
              })

      }*/


    fun requestLogin(userName: String, password: String) {
        api.requestLogin(RequestLoginInfoData(username = userName, password = password))
            .subscribeOn(Schedulers.io())  //상위스트림
            .subscribe({
                Log.d("LoginViewmodel", "response success: $it ")
            }, {
                Log.d("LoginViewmodel", "response fail: $it ")
            })


    }
}
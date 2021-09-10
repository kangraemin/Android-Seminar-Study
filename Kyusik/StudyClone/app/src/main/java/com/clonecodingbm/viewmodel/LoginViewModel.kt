package com.clonecodingbm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clonecodingbm.data.Event
import com.clonecodingbm.data.login.LoginDataItem
import com.clonecodingbm.data.login.LoginInfo
import com.clonecodingbm.network.ApiService
import com.clonecodingbm.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel : ViewModel() {
    companion object {
        const val TAG = "UserLoginViewModel"
    }

    private val apiService: ApiService = RetrofitClient.getApiService
    private val compositeDisposable = CompositeDisposable()

    private val _message = MutableLiveData<Event<String>>()
    private val _loginData = MutableLiveData<LoginDataItem>()

    val message: LiveData<Event<String>> get() = _message
    val loginData: LiveData<LoginDataItem> get() = _loginData

    fun doLoginRequest(id: String, password: String) {
        when {
            // postValue = background 에서 값 변경
            // setValue = mainThread 에서 값 변경
            id.isBlank() -> {
                _message.postValue(Event("아이디를 확인해주세요."))
            }
            password.isBlank() -> {
                _message.postValue(Event("비밀번호를 확인해주세요."))
            }
//            !Patterns.EMAIL_ADDRESS.matcher(id).matches() -> {
//                _message.postValue(Event("이메일형식을 확인해주세요."))
//            }
            else -> {
                compositeDisposable.add(
                    apiService
                        .login(LoginInfo(id, password))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            _loginData.postValue(it)
                        }, {
                            it.printStackTrace()
                            _message.postValue(Event("$it"))
                        })
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
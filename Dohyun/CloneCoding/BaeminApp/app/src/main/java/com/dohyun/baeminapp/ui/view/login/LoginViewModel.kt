package com.dohyun.baeminapp.ui.view.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dohyun.baeminapp.base.BaseViewModel
import com.dohyun.baeminapp.entity.UserInfo
import com.dohyun.baeminapp.network.RetrofitBuilder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import retrofit2.HttpException
import java.lang.IllegalArgumentException

class LoginViewModel : BaseViewModel() {

    val id: MutableLiveData<String> = MutableLiveData("")
    val pw: MutableLiveData<String> = MutableLiveData("")

    private val _isIdEmpty: MutableLiveData<Boolean> = MutableLiveData()
    private val _isPwEmpty: MutableLiveData<Boolean> = MutableLiveData()
    private val _loginErrorMsg: MutableLiveData<Unit> = MutableLiveData()
    private val _successLogin: MutableLiveData<Unit> = MutableLiveData()

    val isIdEmpty: LiveData<Boolean> get() = _isIdEmpty
    val isPwEmpty: LiveData<Boolean> get() = _isPwEmpty
    val loginErrorMsg: LiveData<Unit> get() = _loginErrorMsg
    val successLogin: LiveData<Unit> get() = _successLogin

    fun onLoginClick() {
        val id = id.value.toString().trim()
        val pw = pw.value.toString().trim()

        if (id.isEmpty()) {
            _isIdEmpty.value = true
        } else if (pw.isEmpty()) {
            _isPwEmpty.value = true
        } else {
            _isIdEmpty.value = false
            _isPwEmpty.value = false
            login(id, pw)
        }
    }

    private fun login(id: String, pw: String) {
        RetrofitBuilder.apiClient.login(UserInfo(id, pw))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                // token 저장하기
                Log.d("LoginViewModel", "$it")
                _successLogin.value = Unit
            }, {
                if (it is HttpException) {
                    when (it.code()) {
                        401 -> _loginErrorMsg.value = Unit
                    }
                }
            })

    }
}

class LoginViewModelFactory(
    private val repository: LoginRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class!")
    }
}
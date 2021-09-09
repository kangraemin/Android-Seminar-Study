package com.example.deliveryclonecoding.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.deliveryclonecoding.data.Repository
import com.example.deliveryclonecoding.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import retrofit2.HttpException

class LoginViewModel(
    private val repository: Repository
) : BaseViewModel() {

    private val _loginResult: MutableLiveData<Boolean> = MutableLiveData()
    val loginResult: LiveData<Boolean> = _loginResult

    fun login(id: String, password: String) {
        repository
            .login(id, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _loginResult.value = true
            }, {
                if (it is HttpException && it.code() == 401) {
                    _loginResult.value = false
                } else {
                    it.printStackTrace()
                }
            })
            .addTo(compositeDisposable)
    }
}
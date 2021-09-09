package android.anjahyun.study.viewmodel

import android.anjahyun.study.base.BaseViewModel
import android.anjahyun.study.repository.LoginRepository
import android.anjahyun.study.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel: BaseViewModel() {

    private val repository = LoginRepository()

    private var _loginResult = SingleLiveEvent<Boolean>()
    val loginResult : SingleLiveEvent<Boolean> get() = _loginResult

    fun login(id: String, pw: String) {
        repository.login(id, pw)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isSuccessful) {
                    if (it.code() == 200) {
                        _loginResult.postValue(true)
                    }
                    else _loginResult.postValue(false)
                }
                else _loginResult.postValue(false)
            }, {
                _loginResult.postValue(false)
            })
    }
}
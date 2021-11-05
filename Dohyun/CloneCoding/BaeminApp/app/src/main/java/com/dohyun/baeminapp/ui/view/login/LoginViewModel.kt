package com.dohyun.baeminapp.ui.view.login


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dohyun.baeminapp.ui.base.BaseViewModel
import com.dohyun.baeminapp.data.entity.UserInfo
import com.dohyun.baeminapp.data.repository.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : BaseViewModel() {

    private val _loginState = MutableLiveData<Boolean>()
    val loginState : LiveData<Boolean> = _loginState

    private var disposable: CompositeDisposable? = CompositeDisposable()

    fun startLogin(id: String, pw: String) {
        disposable?.add(
            repository.login(UserInfo(id, pw))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _loginState.postValue(true)
                }, {
                    _loginState.postValue(false)
                    println("LoginViewModel login error : ${it.message}")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

}
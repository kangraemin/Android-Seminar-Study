package com.clonecodingbm.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clonecodingbm.data.remote.login.LoginRequest
import com.clonecodingbm.data.repository.login.LoginRepository
import com.clonecodingbm.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : BaseViewModel() {
    private val _loginResult: MutableLiveData<Boolean> = MutableLiveData()
    val loginResult: LiveData<Boolean> get() = _loginResult

    fun doLoginRequest(id: String, password: String) {
        when {
            id.isBlank() -> {
                showToast("아이디를 확인해주세요.")
            }
            password.isBlank() -> {
                showToast("비밀번호를 확인해주세요.")
            }
            else -> {
                compositeDisposable.add(
                    repository
                        .login(LoginRequest(id, password))
                        .doOnSubscribe { showProgress() }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            hideProgress()
                            _loginResult.value = true
                        }, {
                            it.printStackTrace()
                            hideProgress()
                            _loginResult.value = false
                        })
                )
            }
        }
    }

    companion object {
        private const val TAG = "LoginViewModel"
    }
}
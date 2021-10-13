package com.clonecodingbm.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clonecodingbm.base.BaseViewModel
import com.clonecodingbm.model.Event
import com.clonecodingbm.model.Token
import com.clonecodingbm.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : BaseViewModel() {
    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> get() = _message

    private val _token = MutableLiveData<Token>()
    val token: LiveData<Token> get() = _token

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
                    loginRepository
                        .login(id, password)
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe { showProgress() }
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess { hideProgress() }
                        .subscribe({
                            _token.value = it
                        }, {
                            it.printStackTrace()
                            _message.value = Event("$it")
                        })
                )
            }
        }
    }

    companion object {
        private const val TAG = "LoginViewModel"
    }
}
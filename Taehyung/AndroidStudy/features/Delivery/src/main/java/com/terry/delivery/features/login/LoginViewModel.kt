package com.terry.delivery.features.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.terry.delivery.base.BaseViewModel
import com.terry.delivery.data.DeliveryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-09-08
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val deliveryRepository: DeliveryRepository
) : BaseViewModel() {

//    private val _token = MutableLiveData<Token>()
//    val token: LiveData<Token>
//        get() = _token
//
//    private val _refreshToken = MutableLiveData<String>()
//    val refreshToken: LiveData<String>
//        get() = _refreshToken
//
//    private val _verifyToken = MutableLiveData<Boolean>()
//    val verifyToken: LiveData<Boolean>
//        get() = _verifyToken
//
//    private val _loginError = MutableLiveData<Unit>()
//    val loginError: LiveData<Unit>
//        get() = _loginError

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean>
        get() = _loginResult

    fun login(id: String, password: String) {
        deliveryRepository
            .login(id, password)
            .doOnError {
                _loginResult.postValue(false)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _loginResult.postValue(true)
            }, {
                it.printStackTrace()
            })
            .addTo(disposable)
    }
//    fun getAccessToken(userName: String, password: String) {
//        loginService.getAccessToken(userName, password)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .flatMap { token ->
//                if (token.access.isNullOrEmpty()) {
//                    Single.error(Throwable(token.detail))
//                } else {
//                    Single.just(token)
//                }
//            }
//            .subscribe({ token ->
//                _token.value = token
//            }, {
//                it.printStackTrace()
//                _loginError.value = Unit
//            })
//            .addTo(disposable)
//    }
//
//    fun refreshAccessToken(accessToken: String) {
//        loginService.refreshAccessToken(accessToken)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .flatMap { refreshToken ->
//                if (refreshToken.access.isNullOrEmpty()) {
//                    Single.error(IllegalStateException(refreshToken.detail))
//                } else {
//                    Single.just(refreshToken.access)
//                }
//            }
//            .subscribe({ refreshedToken ->
//                _refreshToken.value = refreshedToken
//            }, {
//                it.printStackTrace()
//            })
//            .addTo(disposable)
//    }
//
//    fun verifyAccessToken(accessToken: String) {
//        loginService.verifyAccessToken("Bearer $accessToken")
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .flatMap { verifyToken ->
//                if (verifyToken.detail.isNullOrEmpty()) {
//                    Single.just(true)
//                } else {
//                    Single.error(IllegalStateException(verifyToken.detail))
//                }
//            }
//            .subscribe({
//                _verifyToken.value = it
//            }, {
//                it.printStackTrace()
//            })
//            .addTo(disposable)
//    }
}
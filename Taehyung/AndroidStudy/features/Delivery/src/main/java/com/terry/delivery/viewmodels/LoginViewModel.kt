package com.terry.delivery.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.terry.delivery.base.BaseViewModel
import com.terry.delivery.remote.entity.Token
import com.terry.delivery.remote.provideLoginService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

/*
 * Created by Taehyung Kim on 2021-09-08
 */
class LoginViewModel : BaseViewModel() {

    private val loginService = provideLoginService()

    private val _token = MutableLiveData<Token>()
    val token: LiveData<Token>
        get() = _token

    private val _refreshToken = MutableLiveData<String>()
    val refreshToken: LiveData<String>
        get() = _refreshToken

    private val _verifyToken = MutableLiveData<Boolean>()
    val verifyToken: LiveData<Boolean>
        get() = _verifyToken

    fun getAccessToken(userName: String, password: String) {
        loginService.getAccessToken(userName, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { token ->
                if (token.access.isNullOrEmpty()) {
                    Single.error(Throwable(token.detail))
                } else {
                    Single.just(token)
                }
            }
            .subscribe({ token ->
                _token.value = token
            }, {
                it.printStackTrace()
            })
            .addTo(disposable)
    }

    fun refreshAccessToken(accessToken: String) {
        loginService.refreshAccessToken(accessToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { refreshToken ->
                if (refreshToken.access.isNullOrEmpty()) {
                    Single.error(IllegalStateException(refreshToken.detail))
                } else {
                    Single.just(refreshToken.access)
                }
            }
            .subscribe({ refreshedToken ->
                _refreshToken.value = refreshedToken
            }, {
                it.printStackTrace()
            })
            .addTo(disposable)
    }

    fun verifyAccessToken(accessToken: String) {
        loginService.verifyAccessToken("Bearer $accessToken")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { verifyToken ->
                if (verifyToken.detail.isNullOrEmpty()) {
                    Single.just(true)
                } else {
                    Single.error(IllegalStateException(verifyToken.detail))
                }
            }
            .subscribe({
                _verifyToken.value = it
            }, {
                it.printStackTrace()
            })
            .addTo(disposable)
    }
}
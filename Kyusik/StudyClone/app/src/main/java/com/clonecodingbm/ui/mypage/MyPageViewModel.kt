package com.clonecodingbm.ui.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clonecodingbm.data.repository.mypage.MyPageRepository
import com.clonecodingbm.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val repository: MyPageRepository
) : BaseViewModel() {
    private val _checkToken = MutableLiveData<Boolean>()
    val checkToken: LiveData<Boolean> get() = _checkToken

    private val _isAutoLogin = MutableLiveData<Boolean>()
    val isAutoLogin: LiveData<Boolean> get() = _isAutoLogin

    init {

    }

    fun isAutoLogin() {
        compositeDisposable.add(
            repository
                .isAutoLogin()
                .doOnSubscribe { showProgress() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    hideProgress()
                    _isAutoLogin.value = true
                    Log.e("TAG", "get isAutoLogin: $it")
                }, {
                    hideProgress()
                    _isAutoLogin.value = false
                    Log.e("TAG", "get isAutoLogin: $it")
                    it.printStackTrace()
                })
        )
    }

    fun checkToken() {
        compositeDisposable.add(
            repository
                .checkToken()
                .doOnSubscribe { showProgress() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    hideProgress()
                    _checkToken.value = true
                }, {
                    it.printStackTrace()
                    hideProgress()
                    _checkToken.value = false
                })
        )
    }
}
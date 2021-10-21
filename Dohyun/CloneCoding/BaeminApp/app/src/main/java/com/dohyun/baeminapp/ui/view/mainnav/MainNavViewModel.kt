package com.dohyun.baeminapp.ui.view.mainnav

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dohyun.baeminapp.data.entity.Token
import com.dohyun.baeminapp.data.repository.mainnav.MainNavRepository
import com.dohyun.baeminapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainNavViewModel @Inject constructor(
    private val repository: MainNavRepository
): BaseViewModel() {

    private val _tokens = MutableLiveData<Token>()

    private val _logoutState = MutableLiveData<Boolean>(false)
    val logoutState: LiveData<Boolean>
        get() = _logoutState

    private var disposable: CompositeDisposable? = CompositeDisposable()


    fun checkUserState() {
        disposable?.add(
            repository.existToken()
                ?.observeOn(Schedulers.io())
                ?.subscribe({
                    if (it != null) {
                        println("MainNavViewModel checkUserState :: $it")
                        _tokens.postValue(it)
                        checkNetWork(it)
                    }
                }, {
                    println("MainNavViewModel checkUserState error :: $it")
                })
        )
    }

    private fun checkNetWork(token: Token) {
        disposable?.add(
            repository.checkNetwork()
                .observeOn(Schedulers.io())
                .subscribe ({
                    verifyToken(token)
                }, {
                    println("MainNavViewModel checkNetWork error $it")
                    logout()
                })
        )
    }

    private fun verifyToken(token: Token) {
        println("MainNavViewModel :: ${_tokens.value}")
        disposable?.add(
            repository.verifyToken(token.access)
                .observeOn(Schedulers.io())
                .subscribe({
                    println("MainNavViewModel verifyToken OK")
                }, {
                    updateToken(token)
                })
        )
    }

    private fun updateToken(tokens: Token) {
        println("MainNavViewModel :: updateToken token : $tokens")
        disposable?.add(
            repository.updateToken(tokens.refresh)
                .observeOn(Schedulers.io())
                .subscribe({
                    println("MainNavViewModel :: UpdateToken $it")
                    val token = Token(tokens.refresh, it)
                    saveAccessToken(token)
                }, {
                    println("MainViewModel updateToken error ${it.message}")
                    logout()
                })
        )
    }

    private fun saveAccessToken(token: Token) {
        disposable?.add(
            repository.saveUpdates(token)
                .observeOn(Schedulers.io())
                .subscribe({
                    println("MainNavViewModel save access Token success")
                }, {
                    println("MainNavViewModel save Access Token error : $it")
                })
        )
    }

    private fun logout() {
        disposable?.add(
            repository.logout()
                .observeOn(Schedulers.io())
                .subscribe({
                    _logoutState.postValue(true)
                    println("MainNavViewModel :: logout!")
                }, {
                    _logoutState.postValue(false)
                    println("Logout error :$it")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}
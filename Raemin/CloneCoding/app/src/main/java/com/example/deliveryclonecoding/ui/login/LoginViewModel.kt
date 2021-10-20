package com.example.deliveryclonecoding.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.deliveryclonecoding.data.Repository
import com.example.deliveryclonecoding.data.remote.base.NetworkCallResult
import com.example.deliveryclonecoding.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val repository: Repository
) : BaseViewModel() {

    private val _loginResult: MutableLiveData<Boolean> = MutableLiveData()
    val loginResult: LiveData<Boolean> = _loginResult

    private val loginSubject = PublishSubject.create<Pair<String, String>>()

    init {
        loginSubject
            .flatMapSingle {
                repository
                    .login(it.first, it.second)
                    .andThen(Single.just(NetworkCallResult<Unit>()))
                    .onErrorReturn { NetworkCallResult(throwable = it) }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it.throwable?.let { throwable ->
                    if (throwable is HttpException && throwable.code() == 401) {
                        _loginResult.value = false
                    } else {
                        throwable.printStackTrace()
                    }
                } ?: run {
                    _loginResult.value = true
                }
            }
            .addTo(compositeDisposable)
    }

    fun login(id: String, password: String) {
        loginSubject.onNext(Pair(id, password))
    }
}
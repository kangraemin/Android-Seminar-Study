package com.example.deliveryclonecoding.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.deliveryclonecoding.data.Repository
import com.example.deliveryclonecoding.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import retrofit2.HttpException
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel() {

    private val _loginResult: MutableLiveData<Boolean> = MutableLiveData()
    val loginResult: LiveData<Boolean> = _loginResult

    private val loginSubject = PublishSubject.create<Pair<String, String>>()

    init {
        loginSubject
            .flatMapCompletable {
                repository
                    .login(it.first, it.second)
            }
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

    fun login(id: String, password: String) {
        loginSubject.onNext(Pair(id, password))
    }
}
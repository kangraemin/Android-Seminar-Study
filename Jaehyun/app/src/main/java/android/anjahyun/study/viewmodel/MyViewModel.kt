package android.anjahyun.study.viewmodel

import android.anjahyun.study.base.BaseViewModel
import android.anjahyun.study.data.local.Token
import android.anjahyun.study.data.local.TokenDao
import android.anjahyun.study.repository.LoginRepository
import android.anjahyun.study.repository.MyRepository
import android.anjahyun.study.util.SingleLiveEvent
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: MyRepository, private val tokenDao: TokenDao): BaseViewModel() {

    private var _isMember = SingleLiveEvent<Boolean>()
    val isMember : SingleLiveEvent<Boolean> get() = _isMember

    fun isMember() {
        tokenDao.getToken()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (!it.accessToken.isNullOrEmpty()) _isMember.postValue(true)
                else _isMember.postValue(false)
            }, {
            })

    }

}
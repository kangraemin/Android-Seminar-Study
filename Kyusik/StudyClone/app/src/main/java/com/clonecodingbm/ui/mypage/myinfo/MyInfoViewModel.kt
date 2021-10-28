package com.clonecodingbm.ui.mypage.myinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.clonecodingbm.data.repository.mypage.MyPageRepository
import com.clonecodingbm.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class MyInfoViewModel @Inject constructor(
    private val repository: MyPageRepository
) : BaseViewModel() {
    private val _isLogout = MutableLiveData<Boolean>()
    val isLogout: LiveData<Boolean> get() = _isLogout

    fun logout() {
        compositeDisposable.add(
            repository
                .logout()
                .doOnSubscribe { showProgress() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    hideProgress()
                    _isLogout.value = true
                },{
                    hideProgress()
                    _isLogout.value = false
                    it.printStackTrace()
                })
        )
    }
}
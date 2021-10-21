package com.dohyun.baeminapp.data.repository.mypage

import com.dohyun.baeminapp.data.local.PreferenceManager
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(
    private val preferenceManager: PreferenceManager
) : MyPageRepository {
    override fun isLogin(): Single<Boolean> {
        return Single.just(preferenceManager.checkLogin)
    }

}
package com.dohyun.baeminapp.data.repository.mypage

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface MyPageRepository {
    fun isLogin(): Single<Boolean>
}
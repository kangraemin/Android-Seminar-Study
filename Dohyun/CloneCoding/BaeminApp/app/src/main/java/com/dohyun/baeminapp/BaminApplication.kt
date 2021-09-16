package com.dohyun.baeminapp

import android.app.Application
import com.dohyun.baeminapp.data.local.AppDatabase
import com.dohyun.baeminapp.data.local.login.LoginLocalDataSourceImpl
import com.dohyun.baeminapp.data.remote.RetrofitClient
import com.dohyun.baeminapp.data.remote.login.LoginRemoteDataSourceImpl
import com.dohyun.baeminapp.data.repository.LoginRepository

class BaminApplication : Application() {
    val database by lazy { AppDatabase.getInstance(this) }
    val repository by lazy { LoginRepository(LoginLocalDataSourceImpl(database!!.tokenDao()), LoginRemoteDataSourceImpl(RetrofitClient.apiClient)) }
}
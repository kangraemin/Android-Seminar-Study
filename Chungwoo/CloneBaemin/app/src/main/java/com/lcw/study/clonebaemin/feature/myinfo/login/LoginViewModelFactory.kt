package com.lcw.study.clonebaemin.feature.myinfo.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lcw.study.clonebaemin.data.login.LoginDatasourceImpl

class LoginViewModelFactory(val loginDatasourceImpl: LoginDatasourceImpl):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(loginDatasourceImpl) as T
    }
}
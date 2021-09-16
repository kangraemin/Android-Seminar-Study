package com.example.deliveryclonecoding.data

import android.content.Context
import com.example.deliveryclonecoding.data.local.AppDatabase
import com.example.deliveryclonecoding.data.local.token.LocalTokenDataSourceImpl
import com.example.deliveryclonecoding.data.remote.RetrofitClient
import com.example.deliveryclonecoding.data.remote.login.LoginDataSourceImpl

class RepositoryFactory {
    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        fun getRepository(context: Context): Repository {
            return INSTANCE ?: synchronized(this) {
                val instance = RepositoryImpl(
                    localTokenDataSource = LocalTokenDataSourceImpl(
                        localTokenDao = AppDatabase.getDatabase(context).tokenDao()
                    ),
                    loginDataSource = LoginDataSourceImpl(
                        loginApi = RetrofitClient.loginAPI
                    )
                )
                INSTANCE = instance
                instance
            }
        }
    }
}
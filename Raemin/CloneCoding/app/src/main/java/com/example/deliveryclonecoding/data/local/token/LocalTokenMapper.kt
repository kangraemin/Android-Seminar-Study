package com.example.deliveryclonecoding.data.local.token

import com.example.deliveryclonecoding.data.remote.login.datasource.LoginDataItem

object LocalTokenMapper {
    fun mappingRemoteDataToLocal(loginDataItem: LoginDataItem): LocalTokenItem {
        return LocalTokenItem(
            accessToken = loginDataItem.access,
            refreshToken = loginDataItem.refresh
        )
    }
}
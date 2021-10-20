package com.example.deliveryclonecoding.data.local.token

import com.example.deliveryclonecoding.data.remote.login.datasource.TokenDataItem

object LocalTokenMapper {
    fun mappingRemoteDataToLocal(tokenDataItem: TokenDataItem): LocalTokenItem {
        return LocalTokenItem(
            accessToken = tokenDataItem.access,
            refreshToken = tokenDataItem.refresh
        )
    }
}
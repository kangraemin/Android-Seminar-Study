package com.clonecodingbm.data.local.token

import com.clonecodingbm.data.remote.login.LoginResponse

object TokenMapper {
    fun mappingRemoteDataToLocal(loginResponse: LoginResponse): TokenEntity {
        return TokenEntity(
            access = loginResponse.access,
            refresh = loginResponse.refresh
        )
    }
}
package com.clonecodingbm.data.local.login

import com.clonecodingbm.data.remote.login.LoginResponse

object UserMapper {
    fun mappingRemoteDataToLocal(userId: String, loginResponse: LoginResponse): UserEntity {
        return UserEntity(
            userId = userId,
            autoLogin = true,
            access = loginResponse.access,
            refresh = loginResponse.refresh
        )
    }
}
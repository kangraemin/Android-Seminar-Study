package com.terry.delivery.data.mapper

import com.terry.delivery.data.local.model.LocalToken
import com.terry.delivery.entity.login.Token

/*
 * Created by Taehyung Kim on 2021-09-28
 */
fun Token.mapToLocalToken() =
    runCatching {
        LocalToken(
            accessToken = access ?: throw NullPointerException(),
            refreshToken = refresh ?: throw NullPointerException()
        )
    }.getOrThrow()

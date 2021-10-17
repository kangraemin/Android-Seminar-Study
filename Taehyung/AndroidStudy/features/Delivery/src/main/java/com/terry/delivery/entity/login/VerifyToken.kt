package com.terry.delivery.entity.login

import com.terry.delivery.entity.NetworkResult

/*
 * Created by Taehyung Kim on 2021-09-08
 */
data class VerifyToken(
    override val detail: String?,
    override val code: String?,
    override val message: List<MessageDto>?
) : NetworkResult

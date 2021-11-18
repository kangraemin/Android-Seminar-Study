package com.terry.delivery.entity

import com.terry.delivery.entity.login.MessageDto

/*
 * Created by Taehyung Kim on 2021-10-17
 */
interface NetworkResult {
    val detail: String?
    val code: String?
    val message: List<MessageDto>?
}

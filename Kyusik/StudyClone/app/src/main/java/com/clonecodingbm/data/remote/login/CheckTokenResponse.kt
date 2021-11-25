package com.clonecodingbm.data.remote.login

data class CheckTokenResponse(
    val detail: String?,
    val code: String?,
    val messages: List<Message>
)

data class Message(
    val token_class: String,
    val token_type: String,
    val message: String
)
package com.example.deliveryclonecoding.data.remote.base

import com.example.deliveryclonecoding.data.remote.login.LoginRepository
import io.reactivex.Flowable
import io.reactivex.SingleTransformer
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class BaseNetworkErrorHandlerImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : BaseNetworkErrorHandler {
    override fun <T> applyNetworkErrorHandler() =
        SingleTransformer<T, T> { single ->
            return@SingleTransformer single
                .retryWhen { errors ->
                    errors
                        .take(5)
                        .map { throwable ->
                            if (throwable is HttpException) {
                                when(throwable.code()) {
                                    401 -> {
                                        return@map NetworkError.Unauthorized
                                    }
                                }
                            }
                            return@map throwable
                        }
                        .flatMap {
                            if (it is NetworkError.Unauthorized) {
                                return@flatMap loginRepository
                                    .refreshToken()
                                    .andThen(Flowable.just(Unit))
                            }
                            if (it is SocketTimeoutException || it is IOException) {
                                return@flatMap Flowable.timer(1000, TimeUnit.MILLISECONDS)
                            }
                            throw it
                        }
                }
        }
}
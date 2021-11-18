package com.terry.delivery

/*
 * Created by Taehyung Kim on 2021-11-11
 */
sealed class State {
    object LoadingState : State()
    data class DataState<T>(val data: T) : State()
    data class ErrorState<T>(val throwable: Throwable) : State()
}

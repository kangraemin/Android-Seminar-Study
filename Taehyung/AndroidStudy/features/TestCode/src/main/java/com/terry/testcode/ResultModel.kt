package com.terry.testcode

/*
 * Created by Taehyung Kim on 2021-08-14
 */
class ResultModel {
    private val resultList = arrayListOf<String>()

    fun saveResultData(
        first: String,
        second: String,
        response: (Boolean, ArrayList<String>) -> Unit
    ) {
        val result = (first.toInt() + second.toInt()).toString()
        Thread.sleep(800) // Mock network delay

        val isSuccess = resultList.add(result + "\n")

        response(isSuccess, resultList)
    }
}
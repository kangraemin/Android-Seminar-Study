package com.terry.testcode.mvc

import com.terry.testcode.ResultModel

/*
 * Created by Taehyung Kim on 2021-08-17
 */
class MvcController {
    private val resultModel: ResultModel by lazy {
        ResultModel()
    }

    fun saveResult(
        first: String,
        second: String,
        response: (Boolean, ArrayList<String>) -> Unit
    ) {
        Thread {
            resultModel.saveResultData(first, second) { isSuccess, resultData ->
                // 모델의 데이터가 변화하면 뷰에게 업데이트 상태를 알린다.
                response(isSuccess, resultData)
            }
        }.start()
    }
}

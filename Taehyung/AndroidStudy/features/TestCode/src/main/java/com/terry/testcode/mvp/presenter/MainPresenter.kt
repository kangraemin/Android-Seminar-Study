package com.terry.testcode.mvp.presenter

import com.terry.testcode.ResultModel

/*
 * Created by Taehyung Kim on 2021-08-14
 */
class MainPresenter(
    private val view: Contract.View
) : Contract.Presenter {

    private val resultModel: ResultModel by lazy {
        ResultModel()
    }

    override fun saveResult(first: String, second: String) {
        Thread {
            view.showProgress()

            saveModelData(first, second)
        }.start()
    }

    private fun saveModelData(first: String, second: String) {
        resultModel.saveResultData(first, second) { isSuccess, resultData ->
            view.hideProgress()

            if (isSuccess) {
                view.showResultData(getAllResult(resultData))
            }
        }
    }

    private fun getAllResult(resultList: ArrayList<String>) =
        resultList.reduce { total, s -> total + s }

}
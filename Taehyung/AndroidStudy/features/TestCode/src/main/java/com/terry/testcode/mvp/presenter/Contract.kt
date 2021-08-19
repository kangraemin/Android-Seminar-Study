package com.terry.testcode.mvp.presenter

/*
 * Created by Taehyung Kim on 2021-08-13
 */
interface Contract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun showResultData(resultData: String)
    }

    interface Presenter {
        fun saveResult(first: String, second: String)
    }

}
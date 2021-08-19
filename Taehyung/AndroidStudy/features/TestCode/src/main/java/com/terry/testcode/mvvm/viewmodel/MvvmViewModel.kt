package com.terry.testcode.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terry.testcode.ResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
 * Created by Taehyung Kim on 2021-08-14
 */
class MvvmViewModel(
    private val resultModel: ResultModel
) : ViewModel() {

    private val _resultData = MutableLiveData<String>()
    val resultData: LiveData<String>
        get() = _resultData

    private val _processState = MutableLiveData<Boolean>()
    val processState: LiveData<Boolean>
        get() = _processState

    fun saveResult(first: String, second: String) = viewModelScope.launch(Dispatchers.IO) {
        _processState.postValue(true)

        resultModel.saveResultData(first, second) { isSuccess, resultData ->
            _processState.postValue(false)

            if (isSuccess) {
                _resultData.postValue(getAllResult(resultData))
            }
        }
    }

    private fun getAllResult(resultList: ArrayList<String>) =
        resultList.reduce { total, s -> total + s }

}

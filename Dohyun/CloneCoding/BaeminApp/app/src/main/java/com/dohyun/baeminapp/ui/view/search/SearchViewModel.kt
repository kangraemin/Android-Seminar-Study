package com.dohyun.baeminapp.ui.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

) : BaseViewModel() {

    private val _value : MutableLiveData<String> = MutableLiveData("")
    val value : LiveData<String>
        get() = _value

    private val _leftRankData = MutableLiveData<List<Rank>>()
    val leftRankData : LiveData<List<Rank>>
        get() = _leftRankData

    private val _rightRankData = MutableLiveData<List<Rank>>()
    val rightRankData : LiveData<List<Rank>>
        get() = _rightRankData


    fun initRankData() {
        _leftRankData.postValue(
                listOf(
                        Rank(1, "bhc", R.drawable.uptriangle),
                        Rank(3, "국수", R.drawable.uptriangle),
                        Rank(5, "자담치킨", R.drawable.downtriangle),
                        Rank(7, "수제비", R.drawable.downtriangle),
                        Rank(9, "써브웨이", R.drawable.uptriangle)
                )
        )

        _rightRankData.postValue(
                listOf(
                        Rank(2, "설빙", R.drawable.downtriangle),
                        Rank(4, "메가커피", R.drawable.uptriangle),
                        Rank(6, "교촌치킨", R.drawable.uptriangle),
                        Rank(8, "배스킨라빈스", R.drawable.uptriangle),
                        Rank(10, "빽다방", R.drawable.downtriangle)
                )
        )
    }
}
package com.dohyun.baeminapp.ui.view.delivery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeliveryViewModel @Inject constructor(

) : BaseViewModel() {

    private val _foodData = MutableLiveData<ArrayList<Food>>()
    val foodData : LiveData<ArrayList<Food>>
        get() = _foodData

    fun initMenu() {
        val menuList = arrayListOf<Food>(
            Food(R.drawable.hand, "1인분"),
            Food(R.drawable.rice, "한식"),
            Food(R.drawable.tteokbokki, "분식"),
            Food(R.drawable.muffins, "카페"),
            Food(R.drawable.sushi, "일식"),

            Food(R.drawable.chicken, "치킨"),
            Food(R.drawable.pizza, "피자"),
            Food(R.drawable.noodles, "아시안"),
            Food(R.drawable.jajangmyeon, "중국집"),
            Food(R.drawable.chop, "족발/보쌈"),

            Food(R.drawable.yummy, "야식"),
            Food(R.drawable.soup, "찜/탕"),
            Food(R.drawable.lunchbox, "도시락"),
            Food(R.drawable.hamburger, "패스트푸드"),
            Food(R.drawable.brand, "브랜드관"),

            Food(R.drawable.crown, "맛집랭킹")
        )

        _foodData.postValue(menuList)
    }
}
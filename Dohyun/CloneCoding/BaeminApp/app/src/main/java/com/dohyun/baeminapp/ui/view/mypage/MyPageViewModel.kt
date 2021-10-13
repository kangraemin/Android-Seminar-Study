package com.dohyun.baeminapp.ui.view.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.data.repository.mypage.MyPageRepository
import com.dohyun.baeminapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val repository: MyPageRepository
) : BaseViewModel() {

    private val _checkTokens = MutableLiveData<Boolean>(false)
    val checkTokens: LiveData<Boolean>
        get() = _checkTokens

    private val _mypageData = MutableLiveData<ArrayList<MyBaemin>>()

    val mypageData: LiveData<ArrayList<MyBaemin>>
        get() = _mypageData

    fun checkUserState() {
        repository.verifyToken()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _checkTokens.value = true
                println("checkUserState OK")
            }, {
                _checkTokens.value = false
                println("error :  $it")
            })
    }

    fun initMyBaeminData(checkToken : Boolean) {

        val menuList = arrayListOf<MyBaemin>()
        menuList.add(
            MyBaemin.OnlyImg(R.drawable.baemin)
        )

        if (!checkToken) {
            menuList.add(MyBaemin.MyUser(R.drawable.ic_user, "로그인해주세요"))
        } else {
            menuList.add(
                MyBaemin.MyUser(R.drawable.ic_user, "이도현 님")
            )
        }

        val menu = listOf(
            MyBaemin.GridSetting(
                R.drawable.ic_dollar, "포인트",
                R.drawable.ic_coupon, "쿠폰함",
                R.drawable.ic_gift, "선물함"
            ),
            MyBaemin.GridSetting(
                R.drawable.ic_heart, "찜",
                R.drawable.ic_receipt, "주문내역",
                R.drawable.ic_comment, "리뷰관리"
            ),
            MyBaemin.BaeminSetImg(R.drawable.ic_tree, "함께해요!"),
            MyBaemin.BaeminSetDesc("배민페이 등록", "배민페이로 결제하면 최대 5.5% 배민포인트가 적립됩니다!"),
            MyBaemin.BaeminSetDesc("가족계정 관리", "결제수단을 공유해 우리 가족의 끼니를 챙겨주세요."),
            MyBaemin.BaeminSet("공지사항"),
            MyBaemin.BaeminSet("배민커넥트 지원"),
            MyBaemin.BaeminSet("이벤트"),
            MyBaemin.BaeminSet("고객센터"),
            MyBaemin.BaeminSet("환경설정"),
            MyBaemin.BaeminSet("약관 및 정책"),
            MyBaemin.BaeminSet("현재 버전 11.6.1")
        )

        menuList.addAll(menu)

        _mypageData.postValue(menuList)
    }


}
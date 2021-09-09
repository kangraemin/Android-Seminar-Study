package com.dohyun.baeminapp.view.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.ToolbarUtil
import com.dohyun.baeminapp.adapter.MyBaemin
import com.dohyun.baeminapp.adapter.MyPageAdapter
import com.dohyun.baeminapp.base.BaseFragment
import com.dohyun.baeminapp.databinding.FragmentMyPageBinding

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private lateinit var adapter: MyPageAdapter

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyPageBinding {
        return FragmentMyPageBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        ToolbarUtil.initToolbar(activity, R.id.mypage_toolbar, this.requireView())

        adapter = MyPageAdapter()
        val decoration = DividerItemDecoration(requireContext(), VERTICAL)
        requireDataBinding().mypageList.addItemDecoration(decoration)
        requireDataBinding().mypageList.adapter = adapter
        requireDataBinding().mypageList.layoutManager = LinearLayoutManager(requireContext())
        setSampleData()
    }

    private fun setSampleData() {
        adapter.addItems(MyBaemin.OnlyImg(resources.getDrawable(R.drawable.baemin, requireContext().theme)))

        adapter.addItems(MyBaemin.MyUser(resources.getDrawable(R.drawable.ic_user, requireContext().theme), "로그인해주세요"))

        adapter.addItems(MyBaemin.GridSetting(
            resources.getDrawable(R.drawable.ic_dollar, requireContext().theme), "포인트",
            resources.getDrawable(R.drawable.ic_coupon, requireContext().theme), "쿠폰함",
            resources.getDrawable(R.drawable.ic_gift, requireContext().theme), "선물함"
        ))
        adapter.addItems(MyBaemin.GridSetting(
            resources.getDrawable(R.drawable.ic_heart, requireContext().theme), "찜",
            resources.getDrawable(R.drawable.ic_receipt, requireContext().theme), "주문내역",
            resources.getDrawable(R.drawable.ic_comment, requireContext().theme), "리뷰관리"
        ))

        adapter.addItems(MyBaemin.BaeminSetImg(resources.getDrawable(R.drawable.ic_tree, requireContext().theme), "함께해요!"))

        adapter.addItems(MyBaemin.BaeminSetDesc("배민페이 등록", "배민페이로 결제하면 최대 5.5% 배민포인트가 적립됩니다!"))
        adapter.addItems(MyBaemin.BaeminSetDesc("가족계정 관리", "결제수단을 공유해 우리 가족의 끼니를 챙겨주세요."))

        adapter.addItems(MyBaemin.BaeminSet("공지사항"))
        adapter.addItems(MyBaemin.BaeminSet("배민커넥트 지원"))
        adapter.addItems(MyBaemin.BaeminSet("이벤트"))
        adapter.addItems(MyBaemin.BaeminSet("고객센터"))
        adapter.addItems(MyBaemin.BaeminSet("환경설정"))
        adapter.addItems(MyBaemin.BaeminSet("약관 및 정책"))
        adapter.addItems(MyBaemin.BaeminSet("현재 버전 11.6.1"))

    }
}
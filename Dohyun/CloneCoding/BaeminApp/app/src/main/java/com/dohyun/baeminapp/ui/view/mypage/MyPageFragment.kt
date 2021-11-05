package com.dohyun.baeminapp.ui.view.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.ui.utils.ToolbarUtil
import com.dohyun.baeminapp.ui.base.BaseFragment
import com.dohyun.baeminapp.databinding.FragmentMyPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val viewModel by activityViewModels<MyPageViewModel>()

    private val myPageAdapter = MyPageAdapter()

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyPageBinding {
        return FragmentMyPageBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        viewModel.checkUserState()
        observeData()
        ToolbarUtil.initToolbar(activity, R.id.mypage_toolbar, this.requireView())
        initMenuList()
    }

    private fun initMenuList() {
        val decoration = DividerItemDecoration(requireContext(), VERTICAL)
        with(requireDataBinding().mypageList) {
            adapter = myPageAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(decoration)
        }
    }

    private fun observeData() {
        with(viewModel) {
            mypageData.observe(viewLifecycleOwner) { data ->
                myPageAdapter.addItems(data)
            }
            loginState.observe(viewLifecycleOwner) { data ->
                if (data == 1) {
                    initMyBaeminData(true)
                } else if (data == 0) {
                    initMyBaeminData(false)
                    updateToken()
                } else initMyBaeminData(false)
            }
        }
    }

}
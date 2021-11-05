package com.dohyun.baeminapp.ui.view.search


import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.ui.utils.ToolbarUtil
import com.dohyun.baeminapp.ui.base.BaseFragment
import com.dohyun.baeminapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val viewModel by activityViewModels<SearchViewModel>()
    private lateinit var rankAdapter: RankAdapter

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        viewModel.checkUserState()
        observeData()
        ToolbarUtil.initToolbar(activity, R.id.search_toolbar, this.requireView())

        requireDataBinding().searchRankListLeft.layoutManager = LinearLayoutManager(requireContext())
        requireDataBinding().searchRankListRight.layoutManager = LinearLayoutManager(requireContext())

        requireDataBinding().searchBar.searchBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            userState.observe(viewLifecycleOwner){
                if (it == 0) {
                    updateUserState()
                }
            }

            requireDataBinding().searchBar.editSearch.setOnKeyListener { v, keyCode, event ->
                if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    goToResult(requireDataBinding().searchBar.editSearch.text.toString())

                    true
                }
                false
            }

            initRankData()
        }

    }

    private fun observeData() {
        viewModel.leftRankData.observe(viewLifecycleOwner) { data ->
            rankAdapter = RankAdapter(data)
            requireDataBinding().searchRankListLeft.adapter = rankAdapter
            rankAdapter.notifyDataSetChanged()
        }

        viewModel.rightRankData.observe(viewLifecycleOwner) { data ->
            rankAdapter = RankAdapter(data)
            requireDataBinding().searchRankListRight.adapter = rankAdapter
            rankAdapter.notifyDataSetChanged()
        }
    }

    private fun goToResult(value: String) {
        val action = SearchFragmentDirections.actionSearchToSearchResult(value)
        requireView().findNavController().navigate(action)
    }

}
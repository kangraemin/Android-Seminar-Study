package com.lcw.study.clonebaemin.feature.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.lcw.study.clonebaemin.R
import com.lcw.study.clonebaemin.databinding.FragmentSearchListBinding
import com.lcw.study.clonebaemin.feature.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchListFragment : BaseFragment<FragmentSearchListBinding>(R.layout.fragment_search_list) {
    private val searchListViewModel: SearchListViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        searchListViewModel.getSearch(
            "iGR3hRvQ.K3wPNmJgqSMeY6CehCZmuq7Kg5Hnw3o7",
            arguments?.getString("query")!!,
            1
        )

        searchListViewModel.searchData.observe(viewLifecycleOwner){
            Log.d("SearchListFragment","searchData : $it")
        }
    }

}
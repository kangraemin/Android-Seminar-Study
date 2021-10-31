package com.terry.delivery.features.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.terry.delivery.R
import com.terry.delivery.base.BaseFragment
import com.terry.delivery.databinding.FragmentMainBinding
import com.terry.delivery.extensions.view.setTopDrawable

/*
 * Created by Taehyung Kim on 2021-08-29
 */
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBottomMenu()
        bindViews()
    }

    private fun initBottomMenu() = with(getViewBinding()) {
        layoutSearchContainer.itemTitleTextView.setTopDrawable(R.drawable.ic_search)
        layoutSearchContainer.itemTitleTextView.text = getString(R.string.search)

        layoutFavoriteContainer.itemTitleTextView.setTopDrawable(R.drawable.ic_favorite)
        layoutFavoriteContainer.itemTitleTextView.text = getString(R.string.favorite)

        layoutOrderContainer.itemTitleTextView.setTopDrawable(R.drawable.ic_order)
        layoutOrderContainer.itemTitleTextView.text = getString(R.string.order_list)

        layoutMyContainer.itemTitleTextView.setTopDrawable(R.drawable.ic_my_page)
        layoutMyContainer.itemTitleTextView.text = getString(R.string.my_page)
    }

    private fun bindViews() = with(getViewBinding()) {
            layoutSearchContainer.parentViewGroup.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToSearchFragment()
                )
            }

            layoutFavoriteContainer.parentViewGroup.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToFavoriteFragment()
                )
            }

            layoutOrderContainer.parentViewGroup.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToOrderListFragment()
                )
            }

            layoutMyContainer.parentViewGroup.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToMyFragment()
                )
            }
    }
}
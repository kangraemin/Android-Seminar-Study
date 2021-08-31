package com.terry.delivery

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.terry.delivery.base.BaseFragment
import com.terry.delivery.databinding.FragmentMainBinding

/*
 * Created by Taehyung Kim on 2021-08-29
 */
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBottomMenu()
        bindViews()
    }

    private fun initBottomMenu() {
        binding?.let { binding ->
            binding.layoutSearchContainer.itemImageView.setImageResource(R.drawable.ic_search)
            binding.layoutSearchContainer.itemTitleTextView.text = getString(R.string.search)

            binding.layoutFavoriteContainer.itemImageView.setImageResource(R.drawable.ic_favorite)
            binding.layoutFavoriteContainer.itemTitleTextView.text = getString(R.string.favorite)

            binding.layoutOrderContainer.itemImageView.setImageResource(R.drawable.ic_order)
            binding.layoutOrderContainer.itemTitleTextView.text = getString(R.string.order_list)

            binding.layoutMyContainer.itemImageView.setImageResource(R.drawable.ic_my_baemin)
            binding.layoutMyContainer.itemTitleTextView.text = getString(R.string.my_baemin)
        }
    }

    private fun bindViews() {
        binding?.let { binding ->
            binding.layoutSearchContainer.parentViewGroup.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToSearchFragment()
                )
            }

            binding.layoutFavoriteContainer.parentViewGroup.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToFavoriteFragment()
                )
            }

            binding.layoutOrderContainer.parentViewGroup.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToOrderListFragment()
                )
            }

            binding.layoutMyContainer.parentViewGroup.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToMyFragment()
                )
            }
        }
    }
}
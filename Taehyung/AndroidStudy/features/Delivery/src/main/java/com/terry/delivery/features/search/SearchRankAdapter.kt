package com.terry.delivery.features.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.terry.delivery.R
import com.terry.delivery.data.remote.model.search.RankingData
import com.terry.delivery.databinding.ItemSearchRankBinding

/*
 * Created by Taehyung Kim on 2021-09-29
 */
class SearchRankAdapter :
    ListAdapter<RankingData, SearchRankAdapter.SearchRankViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRankViewHolder {
        return SearchRankViewHolder(
            ItemSearchRankBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchRankViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class SearchRankViewHolder(
        private val binding: ItemSearchRankBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RankingData) {
            binding.tvRankNum.text = item.rank.toString()
            binding.tvRankTitle.text = item.title


            binding.ivRank.setImageDrawable(
                ContextCompat.getDrawable(
                    binding.ivRank.context,
                    getRankChangedResource(item)
                )
            )
        }

        private fun getRankChangedResource(item: RankingData): Int {
            return when (item.getRankChanged()) {
                RankingData.RankStatus.RANK_UP -> {
                    R.drawable.ic_baseline_arrow_drop_up_24
                }
                RankingData.RankStatus.RANK_DOWN -> {
                    R.drawable.ic_baseline_arrow_drop_down_24
                }
                RankingData.RankStatus.RANK_IDLE -> {
                    R.drawable.ic_baseline_horizontal_rule_24
                }
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<RankingData>() {
            override fun areItemsTheSame(
                oldItem: RankingData,
                newItem: RankingData
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: RankingData,
                newItem: RankingData
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}
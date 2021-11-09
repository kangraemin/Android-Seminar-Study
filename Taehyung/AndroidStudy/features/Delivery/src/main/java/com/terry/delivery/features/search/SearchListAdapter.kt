package com.terry.delivery.features.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.terry.delivery.databinding.ItemSearchBinding
import com.terry.delivery.entity.search.RestaurantDto

/*
 * Created by Taehyung Kim on 2021-11-09
 */
class SearchListAdapter(
    private val onItemClick: (String) -> Unit
) : ListAdapter<RestaurantDto, SearchListAdapter.SearchListViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        return SearchListViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick
        )
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class SearchListViewHolder(
        private val binding: ItemSearchBinding,
        private val onItemClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RestaurantDto) {
            binding.tvSearchTitle.text = item.name
            binding.root.setOnClickListener {
                onItemClick(item.name)
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<RestaurantDto>() {
            override fun areItemsTheSame(oldItem: RestaurantDto, newItem: RestaurantDto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: RestaurantDto,
                newItem: RestaurantDto
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
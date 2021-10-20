package com.clonecodingbm.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clonecodingbm.data.local.recentsearch.RecentSearchEntity
import com.clonecodingbm.databinding.ItemSearchBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private var recentSearches: List<RecentSearchEntity> = listOf()

    interface OnItemClickListener{
        fun onItemClick(v:View, recentSearchEntity: RecentSearchEntity, pos : Int)
        fun deleteItem(query: String)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val recentSearch = recentSearches[position]
        holder.bind(recentSearch)
    }

    override fun getItemCount(): Int = recentSearches.size

    fun setRecentSearches(recentSearches: List<RecentSearchEntity>) {
        this.recentSearches = recentSearches
        notifyDataSetChanged()
    }

    inner class SearchViewHolder(
        private val binding: ItemSearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recentSearch: RecentSearchEntity) {
            itemView.setOnClickListener {
                listener?.onItemClick(it, recentSearch, adapterPosition)
            }
            binding.ivDelete.setOnClickListener {
                listener?.deleteItem(recentSearch.searchWord)
            }
            binding.tvWord.text = recentSearch.searchWord
        }
    }


}
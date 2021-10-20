package com.clonecodingbm.ui.search.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clonecodingbm.data.remote.search.Restaurant
import com.clonecodingbm.databinding.ItemRestaurantBinding

class SearchListAdapter : RecyclerView.Adapter<SearchListAdapter.SearchListViewHolder>() {
    private var restaurants: List<Restaurant> = listOf()

    interface OnItemClickListener{
        fun onItemClick(v:View, restaurant: Restaurant, pos : Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRestaurantBinding.inflate(inflater, parent, false)
        return SearchListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }

    override fun getItemCount(): Int = restaurants.size

    fun setRestaurants(restaurants: List<Restaurant>) {
        this.restaurants = restaurants
        notifyDataSetChanged()
    }

    inner class SearchListViewHolder(
        private val binding: ItemRestaurantBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant) {
            itemView.setOnClickListener {
                listener?.onItemClick(it, restaurant, adapterPosition)
            }
            binding.tvTitle.text = restaurant.name
            binding.tvLatLng.text = restaurant.lat + restaurant.lng
            binding.tvFullAddress.text = restaurant.fullAddress
        }
    }


}
package com.dohyun.baeminapp.ui.view.delivery

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dohyun.baeminapp.R

class ViewPagerAdapter(
        bannerList: ArrayList<Int>
) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    var item = bannerList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent))

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
//        holder.banner.setImageResource(item[position%3])
        Glide.with(holder.banner.context)
                .load(item[position%3])
                .centerCrop()
                .into(holder.banner)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    inner class PagerViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_viewpager, parent, false)) {
        val banner = itemView.findViewById<ImageView>(R.id.item_viewpager_img)
    }

}
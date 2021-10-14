package com.dohyun.baeminapp.ui.view.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dohyun.baeminapp.R

class RankAdapter(val items: List<Rank>): RecyclerView.Adapter<RankAdapter.ViewHolder>() {

    inner class ViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        val titleTV = view?.findViewById<TextView>(R.id.item_rank_title)
        val rankImg = view?.findViewById<ImageView>(R.id.item_rank_img)
        val numTv = view?.findViewById<TextView>(R.id.item_rank_num)

        fun bind(rank: Rank) {
            titleTV!!.text = rank.rankTitle
            numTv!!.text = rank.rankNum.toString()
            rankImg?.setImageResource(rank.rankImg)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rank, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
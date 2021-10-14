package com.dohyun.baeminapp.ui.view.search.result

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dohyun.baeminapp.R
import com.dohyun.baeminapp.data.entity.Restaurant
import com.dohyun.baeminapp.data.entity.Results

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {
    private val resultList = mutableListOf<Restaurant>()

    inner class ViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        val storeTv : TextView = view!!.findViewById<TextView>(R.id.item_result_store)
        val menuTv : TextView = view!!.findViewById(R.id.item_result_menu)
//        val priceTv : TextView = view!!.findViewById(R.id.item_result_price)
//        val img : ImageView = view!!.findViewById<ImageView>(R.id.item_result_img)

        fun bind(result: Restaurant) {
            storeTv.text = result.name
            menuTv.text = result.full_address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultAdapter.ViewHolder, position: Int) {
        holder.bind(resultList[position])
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    fun addItems(resultList : Restaurant) {
//        resultList
        this.resultList.add(resultList)
        this.notifyDataSetChanged()
    }

}
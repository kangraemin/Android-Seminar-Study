package com.dohyun.baeminapp.ui.view.delivery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dohyun.baeminapp.R

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.FoodHolder>() {
    private val items = mutableListOf<Food>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAdapter.FoodHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food_type, parent, false)
        return FoodHolder(view)
    }

    override fun getItemCount(): Int = items.size

    inner class FoodHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        private val img = itemView.findViewById<ImageView>(R.id.item_food_img)
        private val title = itemView.findViewById<TextView>(R.id.item_food_title)

        fun bind(item: Food) {
            img.setImageResource(item.image)
            title.text = item.title
        }
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItems(item: List<Food>) {
        items.clear()
        for (i in item) {
            this.items.add(i)
        }
        this.notifyDataSetChanged()
    }
}
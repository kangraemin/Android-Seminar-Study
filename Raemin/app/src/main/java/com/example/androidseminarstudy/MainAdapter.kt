package com.example.androidseminarstudy

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = SeminarItem.values()

    inner class SeminarItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val btnMoveToExampleDestination: Button = v.findViewById(R.id.btn_move_to_example_destination)

        fun onBind(seminarItem: SeminarItem) {
            btnMoveToExampleDestination.setOnClickListener {
                context.startActivity(Intent(context, seminarItem.seminarActivity))
            }
            btnMoveToExampleDestination.text = context.getString(seminarItem.title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_seminar, parent, false)
        return SeminarItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SeminarItemViewHolder) {
            holder.onBind(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
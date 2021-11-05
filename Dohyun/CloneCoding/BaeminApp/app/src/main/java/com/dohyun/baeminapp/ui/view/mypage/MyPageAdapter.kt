package com.dohyun.baeminapp.ui.view.mypage

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dohyun.baeminapp.R
import java.lang.IllegalStateException

class MyPageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<MyBaemin>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when(viewType) {
        TYPE_USER -> {
            UserHolder.create(parent)
        }
        TYPE_SETTING1 -> {
            SettingHolder.create(parent)
        }
        TYPE_SETTING2 -> {
            SettingDescHolder.create(parent)
        }
        TYPE_SETTING3 -> {
            SettingImgHolder.create(parent)
        }
        TYPE_IMG -> {
            OnlyImgHolder.create(parent)
        }
        TYPE_GRID -> {
            GridSettingHolder.create(parent)
        }
        else -> {
            throw IllegalStateException("Not Found ViewHolder Type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is UserHolder -> {
                holder.bind(items[position] as MyBaemin.MyUser)
            }
            is SettingHolder -> {
                holder.bind(items[position] as MyBaemin.BaeminSet)
            }
            is SettingDescHolder -> {
                holder.bind(items[position] as MyBaemin.BaeminSetDesc)
            }
            is SettingImgHolder -> {
                holder.bind(items[position] as MyBaemin.BaeminSetImg)
            }
            is OnlyImgHolder -> {
                holder.bind(items[position] as MyBaemin.OnlyImg)
            }
            is GridSettingHolder -> {
                holder.bind(items[position] as MyBaemin.GridSetting)
            }
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = when(items[position]) {
        is MyBaemin.MyUser -> {
            TYPE_USER
        }
        is MyBaemin.BaeminSet -> {
            TYPE_SETTING1
        }
        is MyBaemin.BaeminSetDesc -> {
            TYPE_SETTING2
        }
        is MyBaemin.BaeminSetImg -> {
            TYPE_SETTING3
        }
        is MyBaemin.OnlyImg -> {
            TYPE_IMG
        }
        is MyBaemin.GridSetting -> {
            TYPE_GRID
        }
        else -> {
            throw IllegalStateException("Not Found ViewHolder Type")
        }
    }

    class UserHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        private val profileImg = itemView.findViewById<ImageView>(R.id.item_user_profile)
        private val profileName = itemView.findViewById<TextView>(R.id.item_user_id)

        fun bind(item: MyBaemin.MyUser) {
            profileImg.setImageResource(item.userImg)
            profileName.text = item.userName
            itemView.setOnClickListener {
                if (profileName.text == "로그인해주세요") {
                    it.findNavController().navigate(R.id.action_myPage_to_login)
                } else {
                    it.findNavController().navigate(R.id.action_myPage_to_logout)
                }
            }
        }

        companion object Factory {
            fun create(parent: ViewGroup): UserHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_user, parent, false)

                return UserHolder(view)
            }
        }
    }

    class SettingHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        private val text = itemView.findViewById<TextView>(R.id.item_setting_title)
        private val nextBtn = itemView.findViewById<ImageView>(R.id.item_setting_next_btn)

        fun bind(item: MyBaemin.BaeminSet) {
            text.text = item.settingTitle
            itemView.setOnClickListener {
                Log.d("MyPageAdapter", "click setting")
            }
        }

        companion object Factory {
            fun create(parent: ViewGroup): SettingHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_setting, parent, false)

                return SettingHolder(view)
            }
        }
    }

    class SettingDescHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.item_setting_desc_title)
        private val content = itemView.findViewById<TextView>(R.id.item_setting_desc_content)
        private val nextBtn = itemView.findViewById<ImageView>(R.id.item_setting_desc_next_btn)

        fun bind(item: MyBaemin.BaeminSetDesc) {
            title.text = item.settingTitle
            content.text = item.settingDesc
            itemView.setOnClickListener {
                Log.d("MyPageAdapter", "click setting desc")
            }
        }

        companion object Factory {
            fun create(parent: ViewGroup): SettingDescHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_setting_desc, parent, false)

                return SettingDescHolder(view)
            }
        }
    }

    class SettingImgHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        private val img = itemView.findViewById<ImageView>(R.id.item_setting_img_logo)
        private val title = itemView.findViewById<TextView>(R.id.item_setting_img_title)
        private val nextBtn = itemView.findViewById<ImageView>(R.id.item_setting_img_next_btn)

        fun bind(item: MyBaemin.BaeminSetImg) {
            title.text = item.settingTitle
            img.setImageResource(item.settingImg)
            itemView.setOnClickListener {
                Log.d("MyPageAdapter", "click setting img")
            }
        }

        companion object Factory {
            fun create(parent: ViewGroup): SettingImgHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_setting_img, parent, false)

                return SettingImgHolder(view)
            }
        }
    }

    class OnlyImgHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        private val img = itemView.findViewById<ImageView>(R.id.item_only_img)

        fun bind(item: MyBaemin.OnlyImg) {
            img.setImageResource(item.img)

            itemView.setOnClickListener {
                Log.d("MyPageAdapter", "click img")
            }
        }

        companion object Factory {
            fun create(parent: ViewGroup): OnlyImgHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_only_image, parent, false)

                return OnlyImgHolder(view)
            }
        }
    }

    class GridSettingHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        private val logo1 = itemView.findViewById<ImageView>(R.id.item_first_img)
        private val title1 = itemView.findViewById<TextView>(R.id.item_first_title)
        private val logo2 = itemView.findViewById<ImageView>(R.id.item_second_img)
        private val title2 = itemView.findViewById<TextView>(R.id.item_second_title)
        private val logo3 = itemView.findViewById<ImageView>(R.id.item_third_img)
        private val title3 = itemView.findViewById<TextView>(R.id.item_third_title)

        fun bind(item: MyBaemin.GridSetting) {
            logo1.setImageResource(item.logo1)
            logo2.setImageResource(item.logo2)
            logo3.setImageResource(item.logo3)

            title1.text = item.title1
            title2.text = item.title2
            title3.text = item.title3

            itemView.setOnClickListener {
                Log.d("MyPageAdapter", "click grid setting")
            }
        }

        companion object Factory {
            fun create(parent: ViewGroup): GridSettingHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_setting_grid, parent, false)

                return GridSettingHolder(view)
            }
        }

    }

    fun addItems(item: List<MyBaemin>) {
        items.clear()
        for (i in item) {
            this.items.add(i)
        }
        this.notifyDataSetChanged()
    }



    companion object {
        const val TYPE_USER = 0
        const val TYPE_SETTING1 = 1
        const val TYPE_SETTING2 = 2
        const val TYPE_SETTING3 = 3
        const val TYPE_IMG = 4
        const val TYPE_GRID = 5
    }

}
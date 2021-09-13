package com.dohyun.baeminapp.ui.view.mypage

import android.graphics.drawable.Drawable

interface MyBaemin {

    data class MyUser(
        val userImg: Drawable,
        val userName: String
    ): MyBaemin

    data class BaeminSet(
            val settingTitle: String
    ): MyBaemin

    data class BaeminSetDesc(
            val settingTitle: String,
            val settingDesc: String
    ): MyBaemin

    data class BaeminSetImg(
            val settingImg: Drawable,
            val settingTitle: String
    ): MyBaemin

    data class OnlyImg(
            val img: Drawable
    ): MyBaemin

    data class GridSetting(
        val logo1: Drawable,
        val title1: String,
        val logo2: Drawable,
        val title2: String,
        val logo3: Drawable,
        val title3: String
    ): MyBaemin
}
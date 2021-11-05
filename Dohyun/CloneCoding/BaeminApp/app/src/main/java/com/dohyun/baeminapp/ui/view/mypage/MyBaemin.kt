package com.dohyun.baeminapp.ui.view.mypage


interface MyBaemin {

    data class MyUser(
        val userImg: Int,
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
            val settingImg: Int,
            val settingTitle: String
    ): MyBaemin

    data class OnlyImg(
            val img: Int
    ): MyBaemin

    data class GridSetting(
        val logo1: Int,
        val title1: String,
        val logo2: Int,
        val title2: String,
        val logo3: Int,
        val title3: String
    ): MyBaemin
}
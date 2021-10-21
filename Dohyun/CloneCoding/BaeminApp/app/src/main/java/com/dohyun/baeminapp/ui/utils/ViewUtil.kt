package com.dohyun.baeminapp.ui.utils

import android.view.View

fun Boolean.toVisibility(): Int {
    return if (this) View.VISIBLE else View.GONE
}
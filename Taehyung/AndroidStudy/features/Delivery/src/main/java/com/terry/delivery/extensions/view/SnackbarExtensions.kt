package com.terry.delivery.extensions.view

import android.graphics.Color
import com.google.android.material.snackbar.Snackbar

/*
 * Created by Taehyung Kim on 2021-09-07
 */
fun Snackbar.showError() {
    view.setBackgroundColor(Color.parseColor("#B60000"))
    setTextColor(Color.parseColor("#FFFFFF"))
    show()
}
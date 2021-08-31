package com.terry.delivery.extensions.view

import android.widget.TextView
import androidx.core.content.ContextCompat

/*
 * Created by Taehyung Kim on 2021-08-31
 */
fun TextView.setTopDrawable(resId: Int) {
    this.setCompoundDrawablesWithIntrinsicBounds(
        null,
        context?.let { ContextCompat.getDrawable(it, resId) },
        null,
        null
    )
}
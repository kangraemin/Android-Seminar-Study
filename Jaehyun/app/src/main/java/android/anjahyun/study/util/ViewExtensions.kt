package android.anjahyun.study.util

import android.view.View

fun View.visibleStatus(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}
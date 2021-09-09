package com.terry.delivery.util

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager

/*
 * Created by Taehyung Kim on 2021-09-09
 */
object KeyboardHelper {

    fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
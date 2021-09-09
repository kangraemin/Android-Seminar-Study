package com.terry.delivery.util

import android.annotation.SuppressLint
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.terry.delivery.extensions.view.showError

/*
 * Created by Taehyung Kim on 2021-09-07
 */
object SnackbarUtil {

    @SuppressLint("ShowToast")
    fun showErrorMessage(view: View, message: String) =
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).showError()

    fun showMessage(view: View, message: String) =
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}
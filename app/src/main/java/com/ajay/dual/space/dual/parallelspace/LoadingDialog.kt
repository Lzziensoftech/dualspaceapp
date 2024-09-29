package com.ajay.dual.space.dual.parallelspace

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog

class LoadingDialog(context: Context) {

    private var dialog: Dialog

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null)
        dialog = AlertDialog.Builder(context)
            .setView(view)
            .setCancelable(false)
            .create()
    }

    fun show() {
        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    fun dismiss() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }
}
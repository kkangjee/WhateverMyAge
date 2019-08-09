package com.G1B4.WhateverMyAge.Main

import android.app.ProgressDialog
import android.content.Context
import com.G1B4.WhateverMyAge.progressDialog


class Loading (val context: Context) {

    fun loading() {
        //로딩
        android.os.Handler().postDelayed(
            {
                progressDialog = ProgressDialog(context)
                progressDialog!!.setIndeterminate(true)
                progressDialog!!.setMessage("잠시만 기다려 주세요")
                progressDialog!!.show()
                progressDialog!!.setCancelable(false)
            }, 0
        )
    }

    fun loadingEnd() {
        android.os.Handler().postDelayed(
            {
                progressDialog?.dismiss()

            }, 0
        )
    }

}

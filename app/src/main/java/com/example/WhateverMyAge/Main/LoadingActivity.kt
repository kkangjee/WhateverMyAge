package com.example.WhateverMyAge.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.WhateverMyAge.R.layout.activity_loading)


        startLoading()


    }
    private fun startLoading() {
        val handler = Handler()
        handler.postDelayed(Runnable { finish() }, 2000)
    }

}

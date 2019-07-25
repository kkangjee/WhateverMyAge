package com.example.WhateverMyAge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.WhateverMyAge.Main.ConnectServer
import kotlinx.android.synthetic.main.activity_my_information.*

class MyInformation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_information)

        ConnectServer(this).getID(signedin)

        bt_signout.setOnClickListener {
            signedin = 0
            finish()
        }

        bt_back.setOnClickListener {
            finish()
        }
    }
}

package com.example.makeref

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_my_information.*

class MyInformation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_information)

        username.text = signedinname


        bt_signout.setOnClickListener {
            signedin = 0
            finish()
        }
    }
}

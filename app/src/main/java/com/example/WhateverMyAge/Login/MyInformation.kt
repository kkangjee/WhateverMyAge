package com.example.WhateverMyAge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.WhateverMyAge.Guide.Settings.toast
import com.example.WhateverMyAge.Main.ConnectServer
import com.example.WhateverMyAge.Main.MainActivity
import kotlinx.android.synthetic.main.activity_my_information.*

class MyInformation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val server = ConnectServer(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_information)

        //ConnectServer(this).getID(signedin)
        username.text = user_name

        bt_signout.setOnClickListener {
            server.logout()
            Log.i("로그아웃", "ㅇ")
            //signedin = 0
            //finish()
        }

        bt_back.setOnClickListener {
            finish()
        }

        bt_deleteReg.setOnClickListener {
            server.delReg(signedin.toString())
            toast("회원탈퇴")
            Log.i("회원탈퇴 완료", "$signedin")
        }

    }
}

package com.example.WhateverMyAge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import com.example.WhateverMyAge.Guide.Settings.toast
import com.example.WhateverMyAge.Main.ConnectServer
import com.example.WhateverMyAge.Main.MainActivity
import kotlinx.android.synthetic.main.activity_my_information.*

class MyInformation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val server = ConnectServer(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_information)
        val info = intent.getStringArrayExtra("user_info")

        if (signedin != info[0].toInt())  {
            activitytitle.text = "회원 정보"
            findViewById<Button>(R.id.bt_signout).setVisibility(View.GONE)
            findViewById<Button>(R.id.bt_deleteReg).setVisibility(View.GONE)

        }
        else {
            activitytitle.text = "내 정보"
            bt_signout.setOnClickListener {
                server.logout()
                Log.i("로그아웃", "ㅇ")
                //signedin = 0
                //finish()
            }

            bt_deleteReg.setOnClickListener {
                server.delReg(signedin.toString())
                toast("회원탈퇴")
                Log.i("회원탈퇴 완료", "$signedin")
            }
        }

        //ConnectServer(this).getID(signedin)
        username.text = info[1]

        bt_back.setOnClickListener {
            finish()
        }



    }
}

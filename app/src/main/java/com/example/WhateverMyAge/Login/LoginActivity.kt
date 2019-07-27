package com.example.WhateverMyAge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.WhateverMyAge.Main.ConnectServer
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.bt_back

var signedin = 0
var user_name = ""

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bt_loginsubmit.setOnClickListener {
            //var code = 0
            //TODO : 로그인

            var id = ID.text.toString()
            var pw = PW.text.toString()

            ConnectServer(this).Login(id, pw)
        }

        bt_back.setOnClickListener{ //
            finish()
        }

        bt_cantfindid.setOnClickListener {

        }

        bt_cantfindpw.setOnClickListener {

        }

        bt_signup.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.example.WhateverMyAge

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import com.example.WhateverMyAge.Main.Loading
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.WhateverMyAge.Guide.Settings.toast
import com.example.WhateverMyAge.Main.ConnectServer
import com.example.WhateverMyAge.Main.LoadingActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.bt_back
import android.os.AsyncTask.execute

var signedin = 0
var user_name = ""
var progressDialog: ProgressDialog? = null
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bt_loginsubmit.setOnClickListener {
            //var code = 0
            //TODO : 로그인

            var id = ID.text.toString()
            var pw = PW.text.toString()


            if(id==""||pw==""){
                toast("아이디 또는 비밀번호가 비어있습니다")
            }
            else{
                //로딩
                Loading(this).loading()

                ConnectServer(this).Login(id, pw)
//                val intent = Intent(this, LoadingActivity::class.java)
//                startActivity(intent)
            }

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
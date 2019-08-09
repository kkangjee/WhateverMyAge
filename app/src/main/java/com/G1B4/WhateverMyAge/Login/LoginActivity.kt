package com.G1B4.WhateverMyAge

import android.app.ProgressDialog
import android.content.Intent
import com.G1B4.WhateverMyAge.Main.Loading
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.G1B4.WhateverMyAge.Guide.Settings.toast
import com.G1B4.WhateverMyAge.Main.ConnectServer
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.bt_back

var signedin = 0
var user_name = ""
var progressDialog: ProgressDialog? = null
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bt_loginsubmit.setOnClickListener {

            var id = ID.text.toString()
            var pw = PW.text.toString()


            if(id==""||pw==""){
                toast("아이디 또는 비밀번호가 비어있습니다")
            }
            else{
                //로딩
                Loading(this).loading()
                ConnectServer(this).Login(id, pw)
            }
        }

        bt_back.setOnClickListener{ //
            finish()
        }

//        bt_cantfindid.setOnClickListener {
//
//        }
//
//        bt_cantfindpw.setOnClickListener {
//
//        }

        bt_signup.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
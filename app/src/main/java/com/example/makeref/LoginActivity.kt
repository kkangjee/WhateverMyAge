package com.example.makeref

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.bt_back
import kotlinx.android.synthetic.main.activity_love.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bt_loginsubmit.setOnClickListener {
            //TODO : 로그인
        }

        bt_back.setOnClickListener{
            finish()

        }

    }

}
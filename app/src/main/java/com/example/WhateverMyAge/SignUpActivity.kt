package com.example.WhateverMyAge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_love_write_article.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.bt_back
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        bt_back.setOnClickListener {
            finish()
        }

        bt_signup.setOnClickListener {
            //회원가입
            val ID = regID.text.toString()
            val PW = regPW.text.toString()
            val PWC = PWConfirm.text.toString()
            if (ID == "" || PW == "" || PWC == "") {
                toast("빈칸을 채워주세요")
            } else if (PW != PWC) {
                toast("비밀번호가 다릅니다. 확인해주세요.")
            } else {
                ConnectServer(this).registration(ID, PW, PWC)
            }

        }
    }
}
package com.example.WhateverMyAge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.bt_back
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var signedin = 0
var signedinname = ""

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://frozen-cove-44670.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var server = retrofit.create(Service::class.java)

        bt_loginsubmit.setOnClickListener {
            var code = 0
            //TODO : 로그인

            var id = ID.text.toString()
            var pw = PW.text.toString()

            server.login(
                id,
                "",
                pw
            ).enqueue(object : Callback<RegisterForm> {
                override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                    Log.e("서버와 통신에 실패했습니다.", "Error!")
                }

                override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
                    code = response?.code()
                    if (response?.code().toString() == "200") {
                        toast("로그인 성공")

                       // test.text = response?.body().toString()
                        finish()

                        signedin = 1
                        signedinname = id
                    }
                    else
                        toast("로그인 실패")
                }
            })
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
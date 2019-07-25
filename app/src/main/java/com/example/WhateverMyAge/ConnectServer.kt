package com.example.WhateverMyAge
import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_my_information.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ConnectServer (activity : Activity) {
    var activity  = activity
    val retrofit = Retrofit.Builder()
        .baseUrl("https://frozen-cove-44670.herokuapp.com")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

        .build()

    var server = retrofit.create(Service::class.java)


    fun Login (ID : String, PW : String) {
        server.login(
            ID,
            "",
            PW
        ).enqueue(object : Callback<LoginForm> {
            override fun onFailure(call: Call<LoginForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                activity.toast("인터넷이 없습니다.")
            }

            override fun onResponse(call: Call<LoginForm>, response: Response<LoginForm>) {
                //code = response?.code()
                if (response?.code() == 200) {
                    activity.toast("로그인에 성공했습니다.")
                    // test.text = response?.body().toString()
                    signedin = response?.body()?.user?.id!!.toInt()
                    Log.i("id", "$signedin")
                    activity.finish()
                }
            }
        })
    }

    fun getID (id : Int) {
        server.getReg(
            signedin
        ).enqueue(object : Callback<RegisterForm> {
            override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
            }

            override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
                //code = response?.code()
                if (response?.code() == 200) {
                    //activity.toast("로그인 성공")
                    // test.text = response?.body().toString()
                    activity.username.text = "내 이름 : " + response?.body()?.username!!.toString()+", 회원번호 : $signedin"
                }
            }
        })
    }
}
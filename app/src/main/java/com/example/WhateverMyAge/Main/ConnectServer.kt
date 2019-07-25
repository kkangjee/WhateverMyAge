package com.example.WhateverMyAge.Main

import com.example.WhateverMyAge.Guide.toast
import android.app.Activity
import android.util.Log
import com.example.WhateverMyAge.signedin
import kotlinx.android.synthetic.main.activity_my_information.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ConnectServer(activity: Activity) {
    var activity = activity
    val retrofit = Retrofit.Builder()
        .baseUrl("https://frozen-cove-44670.herokuapp.com")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

        .build()

    var server = retrofit.create(Service::class.java)

    fun Login(ID: String, PW: String) {
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

    fun getID(id: Int) {
        server.getReg(
            id
        ).enqueue(object : Callback<RegisterForm> {
            override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                activity.toast("인터넷이 없습니다.")
            }

            override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
                //code = response?.code()
                if (response?.code() == 200) {
                    //activity.toast("로그인 성공")
                    // test.text = response?.body().toString()
                    activity.username.text = "내 이름 : " + response?.body()?.username!!.toString() + ", 회원번호 : $signedin"
                }
            }
        })
    }

    fun registration(ID: String, PW: String, PWC : String) {
        server.postReg(//텍스트를 가져와 보낸다
            ID,
            PW,
            PWC
        ).enqueue(object : Callback<RegisterForm> {

            override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {

                Log.e("서버와 통신 성공!", "Success")
                var code = response?.code()
                //Log.e("multiReg",code.toString())//중복 테스트
                if (code == 400) {
                    activity.toast("이미 있는 아이디 입니다. 다시 입력해주세요")
                } else {
                    activity.toast("반갑습니다. " + activity.regID.text.toString() + "님")
                    activity.finish()
                }


            }

            override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                activity.toast("인터넷이 없습니다.")
            }
        })
    }
}
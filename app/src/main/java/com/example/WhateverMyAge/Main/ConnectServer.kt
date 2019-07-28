package com.example.WhateverMyAge.Main

import com.example.WhateverMyAge.Guide.Settings.toast
import android.app.Activity
import android.util.Log
import com.example.WhateverMyAge.Love.LoveArticles
import com.example.WhateverMyAge.signedin
import com.example.WhateverMyAge.user_name
import kotlinx.android.synthetic.main.activity_love_write_article.*
import kotlinx.android.synthetic.main.activity_my_information.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Multipart
import java.io.File


class ConnectServer(activity: Activity) {
    var activity = activity

    val retrofit = Retrofit.Builder()
        .baseUrl("https://frozen-cove-44670.herokuapp.com/")
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
                if (response.code() == 200) {
                    activity.toast("로그인에 성공했습니다.")
                    // test.text = response?.body().toString()
                    signedin = response.body()?.user?.id!!.toInt()
                    getID(signedin)
                    Log.i("id", "$signedin")
                    activity.finish()
                }
                else {
                    val raw = response.raw().toString()
                    Log.e("error", "$raw")
                    activity.toast("존재하지 않는 ID입니다.")
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
                if (response.code() == 200) {
                    user_name = response.body()?.username!!.toString()

                    //activity.toast("로그인 성공")
                    // test.text = response?.body().toString()
                    //activity.username.text = "내 이름 : " + response.body()?.username!!.toString() + ", 회원번호 : $signedin"
                }
                val body = response.body()?.toString()
                Log.i("error", "$body")
            }
        })
    }

    fun registration(ID: String, PW: String, PWC: String) {
        server.postReg(//텍스트를 가져와 보낸다
            ID,
            PW,
            PWC
        ).enqueue(object : Callback<RegisterForm> {

            override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {

                Log.e("서버와 통신 성공!", "Success")
                var code = response?.code()
                //Log.e("multiReg",code.toString())//중복 테스트
                var raw = response.raw().toString()
                if (code == 201) {
                    activity.toast("반갑습니다. " + activity.regID.text.toString() + "님")
                    activity.finish()
                } else if (code == 400) {
                    activity.toast("이미 있는 아이디 입니다. 다시 입력해주세요")
                }

                Log.i("회원가입 오류", "($raw")
            }

            override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                activity.toast("인터넷이 없습니다.")
            }
        })
    }


    fun delReg(ID: String) {
        server.deleteReg(ID).enqueue(object : Callback<RegisterForm> {
            override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                activity.toast("인터넷이 없습니다.")
            }

            override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
                //code = response?.code()
                if (response.code() == 204) {
                    activity.toast("로그인에 성공했습니다.")
                    // test.text = response?.body().toString()
                    Log.i("회원탈퇴", "$signedin")
                    signedin = 0
                    activity.finish()
                } else {
                    activity.username.text = response?.raw().toString()
                }
            }
        })
    }

    fun logout() {
        server.logout().enqueue(object : Callback<RegisterForm> {
            override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                activity.toast("인터넷이 없습니다.")
            }

            override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
                //code = response?.code()
                if (response.code() == 200) {
                    activity.toast("안녕히 가세요.")
                    // test.text = response?.body().toString()
                    Log.i("로그아웃", "$signedin")
                    signedin = 0
                    activity.finish()
                }

                else {
                    activity.username.text = response?.raw().toString()
                }
            }
        })
    }

    fun writeArticle(title : String) {
        server.postBlog(title).enqueue(object : Callback<RegisterForm> {
            override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                activity.toast("인터넷이 없습니다.")
            }

            override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
                //code = response?.code()
                val raw = response.raw().toString()

                if (response.code() == 200) {
                    //activity.toast("안녕히 가세요.")
                    // test.text = response?.body().toString()
                    Log.i("글쓰기 완료", "$signedin")
                    //signedin = 0
                    //activity.finish()
                }

                else {
                    Log.i("ddd", "$raw")
                }
            }
        })
    }


    fun putPost(id : Int, title : String) {
        server.putPost(id, title).enqueue(object : Callback<PostsForm> {
            override fun onFailure(call: Call<PostsForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
            }

            override fun onResponse(call: Call<PostsForm>, response: Response<PostsForm>) {
                val raw = response.raw().toString()
                val body = response.body()!!.toString()
                if (response?.code().toString() == "200") {
                    // test.text = response?.body().toString()
                }

                else {

                }
                Log.i("dsdsd", "$raw")
                Log.i("body", "$body")
            }
        })
    }

    fun delPost (id :String) {
        server.delPost(id).enqueue(object : Callback<RegisterForm> {
            override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                activity.toast("인터넷이 없습니다.")
            }

            override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
                //code = response?.code()
                if (response.code() == 204) {
                   // activity.toast("로그인에 성공했습니다.")
                    // test.text = response?.body().toString()
                  //  Log.i("회원탈퇴", "$signedin")
                    //activity.finish()
                } else {
                    //activity.username.text = response?.raw().toString()
                }
                val raw = response.raw().toString()
                Log.i("dsds", "$raw")
            }
        })
    }
}
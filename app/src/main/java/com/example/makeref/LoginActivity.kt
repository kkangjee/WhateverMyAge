package com.example.makeref

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.bt_back
import kotlinx.android.synthetic.main.activity_love.*
import kotlinx.android.synthetic.main.activity_love_write_article.*
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

        var server = retrofit.create(Service_test::class.java)
/*

        val retrofit = Retrofit.Builder()
            .baseUrl("https://donghyun.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var server = retrofit.create(Service::class.java)

        fun postSnsButton(pr: EditText, b_contents: EditText, b_created: EditText) {
            server.postSNS(//텍스트를 가져와 보낸다
                pr.text.toString(),
                b_contents.text.toString(),
                b_created.text.toString()

            ).enqueue(object : Callback<PostSnsData> {
                override fun onResponse(call: Call<PostSnsData>, response: Response<PostSnsData>) {
                    //println(response?.body().toString())

                    Log.e("서버와 통신 성공!","Success")
                }
                override fun onFailure(call: Call<PostSnsData>, t: Throwable) {
                    Log.e("서버와 통신에 실패했습니다.","Error!")
                }

            })

        }
        postSnsButton(articleblank,articleblank,articleblank)

*/
        bt_loginsubmit.setOnClickListener {
            var code = 0
            //TODO : 로그인

            var id = ID.text.toString()
            var pw = PW.text.toString()


            server.login(
                id,
                "",
                pw
            ).enqueue(object : Callback<LoginForm> {
                override fun onFailure(call: Call<LoginForm>, t: Throwable) {
                    Log.e("서버와 통신에 실패했습니다.", "Error!")
                }

                override fun onResponse(call: Call<LoginForm>, response: Response<LoginForm>) {
                     code = response?.code()
                    //test.text=response?.code().toString()//받아올 데이터 내용
                   // test.text=reg.toString()//전체 분리한 문자열
                    //tv_3.text= reg[3]//username
                    //tv_4.text= reg[11]//img url
                    if (response?.code().toString() == "200") {
                        toast("로그인 성공")
                        finish()
                        server.getUser(
                            ID.text.toString()
                        ).enqueue(object : Callback<UserForm> {
                            override fun onFailure(call: Call<UserForm>, t:Throwable) {
                                Log.e("서버와 통신에 실패했습니다.", "Error!")
                            }

                            override fun onResponse(call: Call<UserForm>, response: Response<UserForm>) {
                                var user = response?.raw().toString()
                                test.text = user
                            }

                        })

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
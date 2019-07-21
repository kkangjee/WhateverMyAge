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

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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
            //TODO : 로그인
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
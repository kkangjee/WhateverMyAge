package com.example.makeref

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

        val retrofit = Retrofit.Builder()
            .baseUrl("https://frozen-cove-44670.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var server = retrofit.create(Service_test::class.java)


    bt_back.setOnClickListener {
        finish()
    }

        bt_signup.setOnClickListener {
            //회원가입

            var getText1=ID.text.toString()
            var getText2=PW.text.toString()
            var getText3=PWConfirm.text.toString()

            server.postReg(//텍스트를 가져와 보낸다
                getText1,
                getText2,
                getText3
            ).enqueue(object : Callback<RegisterForm>{
                override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
                    //println(response?.body().toString())
                    Log.e("서버와 통신 성공!","Success")
                }
                override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                    Log.e("서버와 통신에 실패했습니다.","Error!")
                }

            })

        }


    }
}

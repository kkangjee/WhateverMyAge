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
         bt_back.setOnClickListener {
             finish()
         }

         bt_signup.setOnClickListener {
             //회원가입
             if(ID.text.toString()==""||PW.text.toString()==""||PWConfirm.text.toString()==""){
                 toast("빈칸을 채워주세요")
             }
             else if(PW.text.toString()!=PWConfirm.text.toString()){
                 toast("비밀번호가 다릅니다. 확인해주세요.")
             }
             else{
                 val retrofit = Retrofit.Builder()
                     .baseUrl("https://frozen-cove-44670.herokuapp.com")
                     .addConverterFactory(GsonConverterFactory.create())
                     .build()

                 var server = retrofit.create(Service::class.java)

                 var code = 0//상태 코드

                 server.postReg(//텍스트를 가져와 보낸다
                     ID.text.toString(),
                     PW.text.toString(),
                     PWConfirm.text.toString()
                 ).enqueue(object : Callback<RegisterForm> {

                     override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {

                         Log.e("서버와 통신 성공!","Success")
                         code = response?.code()
                         //Log.e("multiReg",code.toString())//중복 테스트
                         if(code==400){
                             toast("이미 있는 아이디 입니다. 다시 입력해주세요")
                         }
                         else{
                             toast("반갑습니다. "+ID.text.toString()+"님")
                             finish()
                         }


                     }
                     override fun onFailure(call: Call<RegisterForm>, t: Throwable) {

                         Log.e("서버와 통신에 실패했습니다.","Error!")
                     }

                 })



                 //finish()
             }

         }

    }
}

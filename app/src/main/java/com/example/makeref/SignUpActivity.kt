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

            val retrofit = Retrofit.Builder()
                .baseUrl("https://frozen-cove-44670.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            var server = retrofit.create(Service::class.java)

            if (PW.text.toString() != PWConfirm.text.toString()) {
                toast("비밀번호가 같지 않아요.")
            } else {

                fun postSnsButton(pr: EditText, b_contents: EditText, b_created: EditText) {
                    server.postRegistration(//텍스트를 가져와 보낸다
                        pr.text.toString(), //username
                        "default@default.com", //email
                        b_contents.text.toString(),// password
                        b_created.text.toString() //password confirm
                    ).enqueue(object : Callback<PostRegistrationForm> {
                        override fun onResponse(
                            call: Call<PostRegistrationForm>,
                            response: Response<PostRegistrationForm>
                        ) {
                            //println(response?.body().toString())

                            Log.e("서버와 통신 성공!", "Success")
                        }

                        override fun onFailure(call: Call<PostRegistrationForm>, t: Throwable) {
                            Log.e("서버와 통신에 실패했습니다.", "Error!")
                        }

                    })

                }
                postSnsButton(ID, PW, PWConfirm)

            }
        }


    }
}

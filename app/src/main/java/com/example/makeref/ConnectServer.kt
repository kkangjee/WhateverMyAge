/*package com.example.makeref

import android.util.Log
import android.widget.Button
import android.widget.EditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ConnectServer{
    val retrofit = Retrofit.Builder()
        .baseUrl("https://donghyun.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var server = retrofit.create(Service::class.java)

    fun postSnsButton(pr:EditText,b_contents:EditText,b_created:EditText) {
            server.postSNS(//텍스트를 가져와 보낸다
                pr.text.toString(),
                b_contents.text.toString(),
                b_created.text.toString()

            ).enqueue(object : Callback<PostSnsData>{
                override fun onResponse(call: Call<PostSnsData>, response: Response<PostSnsData>) {
                    //println(response?.body().toString())

                    Log.e("서버와 통신 성공!","Success")
                }
                override fun onFailure(call: Call<PostSnsData>, t: Throwable) {
                    Log.e("서버와 통신에 실패했습니다.","Error!")
                }

            })

    }
//    fun postSnsButton(button: Button,pr:EditText,b_contents:EditText,b_created:EditText) {
//        button.setOnClickListener {
//            server.postSNS(//텍스트를 가져와 보낸다
//                pr.text.toString(),
//                b_contents.text.toString(),
//                b_created.text.toString()
//
//            ).enqueue(object : Callback<PostSnsData>{
//                override fun onResponse(call: Call<PostSnsData>, response: Response<PostSnsData>) {
//                    //println(response?.body().toString())
//
//                    Log.e("서버와 통신 성공!","Success")
//                }
//                override fun onFailure(call: Call<PostSnsData>, t: Throwable) {
//                    Log.e("서버와 통신에 실패했습니다.","Error!")
//                }
//
//            })
//        }
//    }
}*/

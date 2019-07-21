/*package com.example.makeref


import android.util.Log
import android.widget.EditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ConnectServer{
    val retrofit = Retrofit.Builder()
        .baseUrl("https://frozen-cove-44670.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var server = retrofit.create(Service::class.java)


    fun post_Signup(username:EditText,password1:EditText,password2: EditText){//회원가입
        var username_string=username.text.toString()
        var password1_string=password1.text.toString()
        var password2_string=password2.text.toString()

        server.postReg(//텍스트를 가져와 보낸다
            username_string,
            password1_string,
            password2_string
        ).enqueue(object : Callback<RegisterForm> {
            override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
                //println(response?.body().toString())
                Log.e("서버와 통신 성공!","Success")
            }
            override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.","Error!")
            }

        })

    }

    fun get_Register(id:EditText){//회원정보 조회
        server.getReg(
            id.text.toString()
        ).enqueue(object : Callback<RegisterForm>{
            override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.","Error!")
            }
            override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
                var reg= response?.body().toString().split(",","=")
//                tv_1.text=response?.body().toString()//받아올 데이터 내용
//                tv_2.text=reg.toString()//전체 분리한 문자열
//                tv_3.text= reg[3]//username
//                tv_4.text= reg[11]//img url


//                    1 : id: String?=null,
//                    3 : username: String?=null,
//                    5 : password1: String?=null,
//                    7 : password2: String?=null,
//                    9 : email:String?=null,
//                    11 : user_photo:String?=null

                //사진 url 열기
                // intent = Intent(Intent.ACTION_VIEW, Uri.parse(reg[3]))
                //startActivity(intent)
                Log.e("서버와 통신 성공!","Success")

            }
        })
    }
    fun put_modifyRegister(id:EditText,username:EditText,user_photo:EditText){
        server.putReg(
            id.text.toString(),
            username.text.toString(),
            user_photo.text.toString()

        ).enqueue(object : Callback<RegisterForm>{
            override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.","Error!")
            }
            override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
                Log.e("서버와 통신 성공!","Success")

            }
        })

    }
    fun delete_Register(id:EditText){
        server.deleteReg(
            id.text.toString()
        ).enqueue(object : Callback<RegisterForm>{
            override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.","Error!")
            }
            override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
                Log.e("서버와 통신 성공!","Success")

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




}
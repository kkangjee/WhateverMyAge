package com.G1B4.WhateverMyAge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.G1B4.WhateverMyAge.Guide.Settings.toast
import com.G1B4.WhateverMyAge.Main.*
import kotlinx.android.synthetic.main.activity_my_information.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.ShapeDrawable


class MyInformation : AppCompatActivity() {

    val camera = CameraUpload(this)
    val permissioncheck = PermissionCheck(this, this)
    var id: Int = 0
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        camera.newOnActivityResult(id, requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        val server = ConnectServer(this)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://frozen-cove-44670.herokuapp.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

            .build()

        var server2 = retrofit.create(Service::class.java)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_information)

        ProfileUpload.setBackground(ShapeDrawable(OvalShape()))
        ProfileUpload.setClipToOutline(true)

        val info = intent.getStringArrayExtra("user_info")
        id = info[0].toInt()
        var pic: String? = null
        server2.getProfilePic(id).enqueue(object : Callback<Profile> {
            override fun onFailure(call: Call<Profile>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
            }

            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                if (response.code() == 200) {
                    pic = if (response.body()!!.user_photo != null) response.body()!!.user_photo else null
                    Log.i("dsd", "$pic")
                    if (pic != null) {
                        Log.i("pic", "exists" + pic)
                        val bit = ImageURL().setImageURL(pic)
                        ProfileUpload.setImageBitmap(bit)
                        Log.i("image bitmap", "$pic")
                    } else {
                        Log.i("no pic", " dd")
                        val drawable = this@MyInformation.getDrawable(R.drawable.story1)
                        ProfileUpload.setImageDrawable(drawable)
                    }
                }
            }
        })

        if (signedin != info[0].toInt()) {
            activitytitle.text = "회원 정보"
            findViewById<Button>(R.id.bt_signout).setVisibility(View.GONE)
            findViewById<Button>(R.id.bt_deleteReg).setVisibility(View.GONE)

        } else {
            activitytitle.text = "내 정보"
            bt_signout.setOnClickListener {
                server.logout()
                Log.i("로그아웃", "ㅇ")
            }

            bt_deleteReg.setOnClickListener {
                server.delReg(signedin.toString())
                toast("회원탈퇴")
                Log.i("회원탈퇴 완료", "$signedin")
            }
        }

        username.text = info[1]

        bt_back.setOnClickListener {
            finish()
        }

        ProfileUpload.setOnClickListener {
            if (signedin == info[0].toInt()) {
                if (permissioncheck.CameraCheck() == 0 && permissioncheck.GalleryCheck() == 0) {
                    val intent = Intent(this, CameraOrGallery::class.java)
                    startActivityForResult(intent, 1)
                }
                ProfileUpload.setImageDrawable(resources.getDrawable(R.drawable.story1))
            }
        }
    }
}

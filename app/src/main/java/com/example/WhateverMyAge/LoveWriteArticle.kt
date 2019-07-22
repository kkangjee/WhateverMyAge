package com.example.WhateverMyAge

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_love_write_article.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_love_write_article.*
import java.io.File
import android.database.Cursor
import android.graphics.BitmapFactory
import android.os.Environment
import android.provider.MediaStore
import android.util.Log

import kotlinx.android.synthetic.main.activity_love.*
import kotlinx.android.synthetic.main.activity_love_write_article.bt_back

import android.widget.Button
import android.widget.EditText

import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
//import com.example.makeref.ConnectServer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoveWriteArticle : AppCompatActivity() {
    //TODO : CameraUpload 클래스랑 합치기....

    val camera = CameraUpload(this)


    override fun onActivityResult (requestCode : Int, resultCode : Int, data: Intent?) {
        camera.newOnActivityResult(requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val permissioncheck = PermissionCheck(this, this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_love_write_article)
        val cora = intent.getIntExtra("QuestionAnswerArticle", -1)

        //TODO : 사진 촬영 후 업로드 혹은 갤러리 업로드
        //TODO : 카메라, 갤러리 권한

        lovearticlesvideoupload.setOnClickListener {

        }

        lovearticlescancel.setOnClickListener {
            finish()
        }



        bt_back.setOnClickListener {
            finish()

        }
        when (cora) {
            0 -> QuestionAnswerArticle.text = "질문 남기기"
            1 -> QuestionAnswerArticle.text = "답변하기"
            2 -> QuestionAnswerArticle.text = "글쓰기"
            else -> QuestionAnswerArticle.text = "???"
        }



        lovearticlespicupload.setOnClickListener {
            if (permissioncheck.CameraCheck() == 0 && permissioncheck.GalleryCheck() == 0) {
                //pickFromGallery()
                val intent = Intent(this, com.example.WhateverMyAge.CameraOrGallery::class.java)
                startActivityForResult(intent, 1)
            }
        }



        lovearticlescancel.setOnClickListener {
            finish()
        }

        lovearticlessubmit.setOnClickListener {

            toast("$cora")



            if (cora == 0) {
                //질문 작성
            } else if (cora == 1) {
                //질문 답글 작성

            } else if (cora == 2) {
                //사랑방 글 작성
            } else {

            }

            //서버 테스트 구간
/*
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
        }*/
        }


    }

}


/*
*   val context = this
                val intent = Intent(android.content.Intent.ACTION_SEND)
                intent.type = "image/*"
                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                intent.putExtra(
                    Intent.EXTRA_STREAM,
                    FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID, File(filePath))
                )
                val chooser = Intent.createChooser(intent, context.getString(android.R.string.share))
                context.startActivity(chooser)
*
* */
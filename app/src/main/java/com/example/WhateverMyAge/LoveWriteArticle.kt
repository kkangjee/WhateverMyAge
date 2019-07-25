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
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.util.Base64
import kotlinx.android.synthetic.main.activity_love.*
import kotlinx.android.synthetic.main.activity_love_write_article.bt_back

import android.widget.Button
import android.widget.EditText
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
//import com.example.makeref.ConnectServer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream

class LoveWriteArticle : AppCompatActivity() {

    fun filePathToBitmap(imageBitmap : Bitmap) : String {
       // val file = File(filePath)
        //val bOption = BitmapFactory.Options()
        //var imageBitmap = BitmapFactory.decodeFile(file.absolutePath, bOption)

        var stream = ByteArrayOutputStream()
        if (imageBitmap != null) {
            Log.i("이미지가 널 아님", "not null")
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 60, stream)
            var byteArray = stream.toByteArray()
            return Base64.encodeToString(byteArray, Base64.DEFAULT)
        }
        else{
            Log.i("이미지가 널임", "null")
            return ""
        }
    }

    val camera = CameraUpload(this)

    override fun onActivityResult (requestCode : Int, resultCode : Int, data: Intent?) {
        camera.newOnActivityResult(requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://frozen-cove-44670.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var server = retrofit.create(Service::class.java)

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
                val intent = Intent(this, com.example.WhateverMyAge.CameraOrGallery::class.java)
                startActivityForResult(intent, 1)
            }
        }

        lovearticlescancel.setOnClickListener {
            finish()
        }

        lovearticlessubmit.setOnClickListener {
            if (cora == 0) {
                //질문 작성
            }

            else if (cora == 1) {
                //질문 답글 작성

            }

            else if (cora == 2) {
                //사랑방 글 작성
               // val file = File(cameraFilePath)
                if (file == null) {
                    Log.i("파일이","null")
                }

                val name = file!!.getName()

               // Log.i("file path", "$cameraFilePath")
                //이미지 업로드 via MultiPart (failed)
              //  val json = Gson().toJson(RegisterForm())
                val fileReqBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                val part = MultipartBody.Part.createFormData("user_photo", name, fileReqBody)
                Log.i("file name", "$name")
              //  val description = RequestBody.create(MediaType.parse("multipart/form-data"), json)
                val descriptionPart = RequestBody.create(MultipartBody.FORM, "user_photo")

                //val bitmapImage = filePathToBitmap(cameraFilePath!!)

                server.uploadPic(
                    "10",
                    file!!
                    //descriptionPart
                ).enqueue(object : Callback<RegisterForm> {
                    override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                        Log.e("서버와 통신에 실패했습니다.", "Error!")
                        raw.text = name
                    }

                    override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
                        if (response?.code().toString() == "200") {
                            toast("로그인 성공")

                            // test.text = response?.body().toString()

                        }
                        else
                            toast("로그인 실패")

                        raw.text = response?.raw().toString()
                        code.text = name
                        body.text = response?.body().toString()


                    }
                })
            }


            else {

            }
        }

    }
}
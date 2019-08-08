package com.example.WhateverMyAge.Love

import android.content.Context
import com.example.WhateverMyAge.Guide.Settings.toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_love_write_article.*
import android.graphics.Bitmap
import android.util.Log
import android.util.Base64
import android.widget.EditText
import com.example.WhateverMyAge.Guide.Questions.QnA
import com.example.WhateverMyAge.Main.*
import com.example.WhateverMyAge.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_love_write_article.bt_back

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

//import com.example.makeref.ConnectServer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import java.io.File

class LoveWriteArticle : AppCompatActivity() {
    public val camera = CameraUpload(this)
    public lateinit var context : Context
    override fun onActivityResult (requestCode : Int, resultCode : Int, data: Intent?) {
        camera.newOnActivityResult(0, requestCode, resultCode, data)
    }

    public fun uploadingImage (file : File) {
        image = file
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //val connect = ConnectServer(this)
        context = this
        val permissioncheck = PermissionCheck(this, this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_love_write_article)
        val cora = intent.getIntExtra("QuestionAnswerArticle", -1)

        //TODO : 사진 촬영 후 업로드 혹은 갤러리 업로드
        //TODO : 카메라, 갤러리 권한



        lovearticlescancel.setOnClickListener {
            finish()
        }

        bt_back.setOnClickListener {
            finish()
        }

        var content : Array<String> = arrayOf()

        when (cora) {
            0 -> QuestionAnswerArticle.text = "질문 남기기"
            1 -> QuestionAnswerArticle.text = "답변하기"
            2 -> QuestionAnswerArticle.text = "글쓰기"
            3 -> {QuestionAnswerArticle.text = "글 수정"

                content = intent.getStringArrayExtra("put")!!

                val putcontent = findViewById<EditText>(R.id.articlecontent)
                putcontent.setText(content[1])

            }
            4 -> {
                QuestionAnswerArticle.text = "질문 수정"
                content = intent.getStringArrayExtra("putQ")!!
                val putcontent = findViewById<EditText>(R.id.articlecontent)
                putcontent.setText(content[1])
            }
            else -> QuestionAnswerArticle.text = "???"
        }

        lovearticlespicupload.setOnClickListener {
            if (permissioncheck.CameraCheck() == 0 && permissioncheck.GalleryCheck() == 0) {
                val intent = Intent(this, CameraOrGallery::class.java)
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
                var name = "ddd"
                if (image != null) {
                    Log.i("파일이", "null")
                    name = image!!.getName()
                    Log.i("image", " " + image!!.getName())

//
//                    val fileReqBody = RequestBody.create(MediaType.parse("image/*"), image)
//                    val part = MultipartBody.Part.createFormData("picture", name, fileReqBody)

                    Log.i("file name", "$name")
                    // val description = RequestBody.create(MediaType.parse("description"), "picture")
                    //  Log.i("part", "$part")
                    //  Log.i("decription", "$description")
                    val descriptionPart = RequestBody.create(MultipartBody.FORM, "user_photo")

                    //성공코드 : 지우지 마시오!!
                    //ConnectServer(this).uploadPic(part, articlecontent.text.toString())
                    // ConnectServer(this).getPic()
                    val actual = articlecontent.text.toString()
                    ConnectServer(this).postQuestion(if (actual.lastIndex > 10) actual.substring(0,10) else actual ,actual, image!!)
                    //  ConnectServer(this).putPost(4, "sssss")
                    //  ConnectServer(this).delPost(articletitle.text.toString())
                    finish()
                    image = null
                }
                else {
                    val actual = articlecontent.text.toString()
                    ConnectServer(this).postQuestion(if (actual.lastIndex > 10) actual.substring(0,10) else actual ,actual, null)
                }


                // val LA = _Love_Activity
                /// LA.finish()
//
//                val CA = _Comment_Activity
//                CA.finish()

                val intent = Intent(this, QnA::class.java)
                startActivity(intent)
            }

            else if (cora == 1) {
                //질문 답글 작성


            }

            else if (cora == 2) {
                var name = "ddd"
                //사랑방 글 작성
                if (image != null) {
                    Log.i("파일이", "null")
                    name = image!!.getName()
                    Log.i("image", " " + image!!.getName())

//
//                    val fileReqBody = RequestBody.create(MediaType.parse("image/*"), image)
//                    val part = MultipartBody.Part.createFormData("picture", name, fileReqBody)

                    Log.i("file name", "$name")
                    // val description = RequestBody.create(MediaType.parse("description"), "picture")
                  //  Log.i("part", "$part")
                    //  Log.i("decription", "$description")
                    val descriptionPart = RequestBody.create(MultipartBody.FORM, "user_photo")

                    //성공코드 : 지우지 마시오!!
                    //ConnectServer(this).uploadPic(part, articlecontent.text.toString())
                    // ConnectServer(this).getPic()
                    val actual = articlecontent.text.toString()
                    ConnectServer(this).writeArticle(if (actual.lastIndex > 10) actual.substring(0,10) else actual ,actual, lat, lng, image!!)
                    //  ConnectServer(this).putPost(4, "sssss")
                    //  ConnectServer(this).delPost(articletitle.text.toString())
                    finish()
                    image = null
                }
                else {
                    val actual = articlecontent.text.toString()
                    ConnectServer(this).writeArticle(if (actual.lastIndex > 10) actual.substring(0,10) else actual ,actual, lat, lng, null)
                }


                val LA = _Love_Activity
                LA.finish()
//
//                val CA = _Comment_Activity
//                CA.finish()

                val intent = Intent(this, LoveActivity::class.java)
                startActivity(intent)
            }

            else if (cora==3) {
                //글 수정
                val actual = articlecontent.text.toString()
                ConnectServer(this).putPost(content[0].toInt(), if (actual.lastIndex > 10) actual.substring(0,10) else actual ,actual, image)
                finish()
                val LA = _Love_Activity
                LA.finish()

                val CA = _Comment_Activity
                CA.finish()

                val intent = Intent(this, LoveActivity::class.java)
                startActivity(intent)
            }

            else if (cora == 4) {
                val actual = articlecontent.text.toString()
                ConnectServer(this).putQuestion(content[0].toInt(),  if (actual.lastIndex > 10) actual.substring(0,10) else actual ,actual, image)
                finish()
            }
        }
    }
}
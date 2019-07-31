package com.example.WhateverMyAge.Love

import com.example.WhateverMyAge.Guide.Settings.toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_love_write_article.*
import android.graphics.Bitmap
import android.util.Log
import android.util.Base64
import android.widget.EditText
import com.example.WhateverMyAge.Main.*
import com.example.WhateverMyAge.R
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
        //val connect = ConnectServer(this)

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


//                val name = file!!.getName()
//
//               // Log.i("file path", "$cameraFilePath")
//                //이미지 업로드 via MultiPart (failed)
//              //  val json = Gson().toJson(RegisterForm())
//                val fileReqBody = RequestBody.create(MediaType.parse("image/*"), file)
//                val part = MultipartBody.Part.createFormData("picture", name, fileReqBody)
//                Log.i("file name", "$name")
//              //  val description = RequestBody.create(MediaType.parse("multipart/form-data"), json)
//                val descriptionPart = RequestBody.create(MultipartBody.FORM, "user_photo")

                //val bitmapImage = filePathToBitmap(cameraFilePath!!)

                //ConnectServer(this).uploadPic(part)
                val actual = articlecontent.text.toString()
                ConnectServer(this).writeArticle(if (actual.lastIndex > 10) actual.substring(0,10) else actual ,actual, lat, lng)
              //  ConnectServer(this).putPost(4, "sssss")
              //  ConnectServer(this).delPost(articletitle.text.toString())

                finish()
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
                ConnectServer(this).putPost(content[0].toInt(), if (actual.lastIndex > 10) actual.substring(0,10) else actual ,actual)
                finish()
                val LA = _Love_Activity
                LA.finish()

                val CA = _Comment_Activity
                CA.finish()

                val intent = Intent(this, LoveActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
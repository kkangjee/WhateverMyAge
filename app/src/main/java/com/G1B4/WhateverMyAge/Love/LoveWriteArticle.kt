package com.G1B4.WhateverMyAge.Love

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_love_write_article.*
import android.util.Log
import android.widget.EditText
import com.G1B4.WhateverMyAge.Guide.Questions.QnA
import com.G1B4.WhateverMyAge.Main.*
import com.G1B4.WhateverMyAge.R
import kotlinx.android.synthetic.main.activity_love_write_article.bt_back

//import com.example.makeref.ConnectServer

class LoveWriteArticle : AppCompatActivity() {
     val camera = CameraUpload(this)
     lateinit var context : Context
    override fun onActivityResult (requestCode : Int, resultCode : Int, data: Intent?) {
        camera.newOnActivityResult(0, requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //val connect = ConnectServer(this)
        context = this
        val permissioncheck = PermissionCheck(this, this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_love_write_article)
        val cora = intent.getIntExtra("QuestionAnswerArticle", -1)

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
            if (cora == 0) {
                //질문 작성
                var name = "ddd"
                if (image != null) {
                    Log.i("파일이", "null")
                    name = image!!.getName()

                    //성공코드 : 지우지 마시오!!
                    val actual = articlecontent.text.toString()
                    ConnectServer(this).postQuestion(if (actual.lastIndex > 10) actual.substring(0,10) else actual ,actual, image!!)
                    finish()
                    image = null
                }
                else {
                    val actual = articlecontent.text.toString()
                    ConnectServer(this).postQuestion(if (actual.lastIndex > 10) actual.substring(0,10) else actual ,actual, null)
                }

                val intent = Intent(this, QnA::class.java)
                startActivity(intent)
            }

            else if (cora == 2) {
                var name = "ddd"
                //사랑방 글 작성
                if (image != null) {
                    Log.i("파일이", "null")
                    name = image!!.getName()
                    Log.i("image", " " + image!!.getName())
                    Log.i("file name", "$name")

                    val actual = articlecontent.text.toString()
                    ConnectServer(this).writeArticle(if (actual.lastIndex > 10) actual.substring(0,10) else actual ,actual, lat, lng, image!!)

                    finish()
                    image = null

                }
                else {
                    val actual = articlecontent.text.toString()
                    ConnectServer(this).writeArticle(if (actual.lastIndex > 10) actual.substring(0,10) else actual ,actual, lat, lng, null)
                }


                val LA = _Love_Activity
                LA.finish()

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
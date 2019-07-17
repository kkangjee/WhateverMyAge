package com.example.makeref

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_love_write_article.*

class LoveWriteArticle : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_love_write_article)
        val cora = intent.getIntExtra("QuestionArticleAnswer", -1)

        //TODO : 사진 촬영 후 업로드 혹은 갤러리 업로드
        //TODO : 카메라, 갤러리 권한

        lovearticlespicupload.setOnClickListener {

        }

        lovearticlesvideoupload.setOnClickListener {

        }

        lovearticlescancel.setOnClickListener {
            finish()
        }

        lovearticlessubmit.setOnClickListener {
            if (cora == 0) {
                //질문 작성
            }

            else if (cora == 1) {
                //사랑방 글 작성

            }

            else if (cora == 2) {
                //질문 답글 작성
            }
            else {

            }
        }


    }
}

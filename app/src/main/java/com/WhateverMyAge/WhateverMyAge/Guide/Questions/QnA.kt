package com.WhateverMyAge.WhateverMyAge.Guide.Questions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.WhateverMyAge.WhateverMyAge.Love.LoveWriteArticle
import com.WhateverMyAge.WhateverMyAge.Main.QuestionForm
import com.WhateverMyAge.WhateverMyAge.Main.Service
import com.WhateverMyAge.WhateverMyAge.R
import kotlinx.android.synthetic.main.activity_qna.*
import kotlinx.android.synthetic.main.activity_qna.bt_back
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class QnA : AppCompatActivity() {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://frozen-cove-44670.herokuapp.com/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

        .build()

    var server = retrofit.create(Service::class.java)

    fun showQuestions(questionlist: ArrayList<QuestionsTitles>) {
        server.showQuestions().enqueue(object : Callback<List<QuestionForm>> {
            override fun onFailure(call: Call<List<QuestionForm>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<QuestionForm>>, response: Response<List<QuestionForm>>) {
                val count = response.body()!!.lastIndex
                val body = response.body()!!
                Log.i("dsdsd", "$count")
                Log.i("body", "$body")

                if (response.code().toString() == "200") {
                    for (i in 0..count) {
                        questionlist.add(QuestionsTitles(body[i].q_title!!, body[i].id, body[i].q_content, body[i].q_photo, body[i].author_username, body[i].author_id, body[i].cnt))
                    }
                    val questions = QuestionTitlesAdapter(this@QnA, questionlist, this@QnA)
                    Questions.adapter = questions

                    val questionslm = LinearLayoutManager(this@QnA)
                    Questions.layoutManager = questionslm
                    Questions.setHasFixedSize(true)
                }
            }

        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qna)

        var faqlist = arrayListOf(
            FAQTitles("화면이 너무 어두워요, 밝아요", 0),
            FAQTitles("벨소리 크기를 조절하고 싶어요", 1),
            FAQTitles("음악, 동영상 크기를 조절하고 싶어요", 2),
            FAQTitles("전화번호가 지워졌어요, 저장할래요", 3),
            FAQTitles("카카오톡, 유튜브를 쓰고 싶어요", 4)
            )


        val faq = FAQTitlesAdapter(this, faqlist, this)
        FAQ.adapter = faq

        val faqlm = LinearLayoutManager(this)
        FAQ.layoutManager = faqlm
        FAQ.setHasFixedSize(true)

        var questionlist : ArrayList<QuestionsTitles> = arrayListOf()
        showQuestions(questionlist)

        bt_writeQuestion.setOnClickListener {
            //setting 화면
            val intent = Intent(this, LoveWriteArticle::class.java)
            intent.putExtra("QuestionAnswerArticle", 0)  //질문 작성
            startActivity(intent)
        }

        bt_back.setOnClickListener {
            finish()
        }
    }
}

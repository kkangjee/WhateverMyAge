package com.example.WhateverMyAge.Guide.Questions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.WhateverMyAge.Love.LoveWriteArticle
import com.example.WhateverMyAge.Main.QuestionForm
import com.example.WhateverMyAge.Main.Service
import com.example.WhateverMyAge.R
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
                //contentlist = arrayListOf()
                val body = response.body()!!
                Log.i("dsdsd", "$count")
                Log.i("body", "$body")

                if (response.code().toString() == "200") {
                    for (i in 0..count) {
                        questionlist.add(QuestionsTitles(body[i].q_title!!, body[i].id, body[i].q_content, body[i].q_photo, body[i].author_username, body[i].author_id))

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
            FAQTitles("전화번호가 지워졌어요"),
            FAQTitles("카카오톡이 안 돼요"),
            FAQTitles("알림이 안 울려요"),
            FAQTitles("밴드 가입이 안 돼요")
        )


        val faq = FAQTitlesAdapter(this, faqlist)
        FAQ.adapter = faq

        val faqlm = LinearLayoutManager(this)
        FAQ.layoutManager = faqlm
        FAQ.setHasFixedSize(true)
//
//        var questionlist = arrayListOf(
//            QuestionsTitles("소리가 이상하게 들려요"),
//            QuestionsTitles("카카오톡 동영상 보내고 싶어요"),
//            QuestionsTitles("일정 확인이 안 돼요"),
//            QuestionsTitles("어플이 사라졌어요")
//        )
        var questionlist : ArrayList<QuestionsTitles> = arrayListOf()
        showQuestions(questionlist)
//
//
//
//        val questions = QuestionTitlesAdapter(this, questionlist)
//        Questions.adapter = questions
//
//        val questionslm = LinearLayoutManager(this)
//        Questions.layoutManager = questionslm
//        Questions.setHasFixedSize(true)

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

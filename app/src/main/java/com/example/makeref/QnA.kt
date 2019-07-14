package com.example.makeref

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_qna.*

class QnA : AppCompatActivity() {

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

        var questionlist = arrayListOf(
            QuestionsTitles("소리가 이상하게 들려요"),
            QuestionsTitles("카카오톡 동영상 보내고 싶어요"),
            QuestionsTitles("일정 확인이 안 돼요"),
            QuestionsTitles("어플이 사라졌어요")
            )

        val questions = QuestionTitlesAdapter(this, questionlist)
        Questions.adapter = questions

        val questionslm = LinearLayoutManager(this)
        Questions.layoutManager = questionslm
        Questions.setHasFixedSize(true)

        bt_writeQuestion.setOnClickListener {//setting 화면
            val intent = Intent(this, LoveWriteArticle::class.java)
            startActivity(intent)
        }

    }


}

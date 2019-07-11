package com.example.makeref

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_love.*
import kotlinx.android.synthetic.main.lovearticles.*

class LoveActivity : AppCompatActivity() {
    var contentlist = arrayListOf(
        LoveArticles("story1", "sarangbang", "오늘은 여기에 놀러 갔어요", "3", "5"),
        LoveArticles("story2", "whats wrong", "멋쟁이 사자처럼 화이팅", "32", "6"),
        LoveArticles("story3", "with my age", "내 나이가 어때서", "9", "5"),
        LoveArticles("story4", "lets do this", "ㅎㅎㅎ", "7", "15")
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_love)

        val love = LoveArticlesAdapter(this, contentlist)
        lovearticles.adapter = love

        val lm = LinearLayoutManager(this)
        lovearticles.layoutManager = lm
        lovearticles.setHasFixedSize(true)

        bt_writeArticle.setOnClickListener {//setting 화면
            val intent = Intent(this, LoveWriteArticle::class.java)
            startActivity(intent)
        }

    }


}
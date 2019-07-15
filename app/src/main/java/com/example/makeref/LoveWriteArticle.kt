package com.example.makeref

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoveWriteArticle : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_love_write_article)
        val cora = intent.getIntExtra("CommentOrArticle", -1)
        if (cora == 1) {
            //게시글에 등록
        }
        
        else if (cora == 0) {
            //댓글에 등록
            
        }
    }
}

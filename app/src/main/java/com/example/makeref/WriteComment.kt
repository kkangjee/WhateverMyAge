package com.example.makeref

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_write_comment.*

class WriteComment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_comment)

        lovecommentcancel.setOnClickListener {
            finish()
        }


        lovecommentsubmit.setOnClickListener {
            //TODO : 댓글 업로드
        }
    }
}

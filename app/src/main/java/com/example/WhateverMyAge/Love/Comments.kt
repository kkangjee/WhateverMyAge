package com.example.WhateverMyAge.Love

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.WhateverMyAge.Guide.Settings.toast
import com.example.WhateverMyAge.Main.ConnectServer
import com.example.WhateverMyAge.R
import com.example.WhateverMyAge.signedin
import kotlinx.android.synthetic.main.activity_comments.*

class Comments : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        val ID = intent.getIntExtra("ID", 0)
        ConnectServer(this).getPost(ID, this)
        val commentlist = arrayListOf(
            Comment("sarang", "사진 잘 봤어요!"),
            Comment("big_guy", "좋아요!")
        )

        ConnectServer(this).getComment(ID)


        val comment = CommentsAdapter(this, commentlist)
        commentslist.adapter = comment

        val lm = LinearLayoutManager(this)
        commentslist.layoutManager = lm
        commentslist.setHasFixedSize(true)


        bt_back.setOnClickListener {
            finish()
        }

        bt_submitComment.setOnClickListener {
            if (signedin == 0) {
                toast("댓글을 작성하려면 로그인하세요")
            }
            else {
                ConnectServer(this).postComment(ID, reply.text.toString())
            }
        }

    }
}

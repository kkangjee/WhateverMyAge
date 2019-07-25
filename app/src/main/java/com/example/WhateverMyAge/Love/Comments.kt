package com.example.WhateverMyAge.Love

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.WhateverMyAge.R
import kotlinx.android.synthetic.main.activity_comments.*

class Comment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        val commentlist = arrayListOf(
            Comments("sarang", "사진 잘 봤어요!"),
            Comments("big_guy", "좋아요!")
        )

        val comment = CommentsAdapter(this, commentlist)
        commentslist.adapter = comment

        val lm = LinearLayoutManager(this)
        commentslist.layoutManager = lm
        commentslist.setHasFixedSize(true)
    }
}

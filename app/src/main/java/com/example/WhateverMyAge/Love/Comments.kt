package com.example.WhateverMyAge.Love

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.WhateverMyAge.Guide.Settings.toast
import com.example.WhateverMyAge.Main.CommentsForm
import com.example.WhateverMyAge.Main.ConnectServer
import com.example.WhateverMyAge.Main.Service
import com.example.WhateverMyAge.R
import com.example.WhateverMyAge.signedin
import kotlinx.android.synthetic.main.activity_comments.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class Comments : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://frozen-cove-44670.herokuapp.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

            .build()

        var server = retrofit.create(Service::class.java)

        val ID = intent.getIntExtra("ID", 0)
        ConnectServer(this).getPost(ID, this)

        var commentlist : ArrayList<Comment> = arrayListOf()

        server.getComment(ID).enqueue(object : Callback<List<CommentsForm>> {
            override fun onFailure(call: Call<List<CommentsForm>>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
            }

            override fun onResponse(call: Call<List<CommentsForm>>, response: Response<List<CommentsForm>>) {
                val raw = response.raw().toString()
                if (response.code().toString() == "200") {

                    val body = response.body()!!
                    // test.text = response?.body().toString()
                    val cnt = body.lastIndex

                    Log.i("댓글 수", "$cnt")

                    for (i in 0..cnt) {
                        Log.i("댓글 추가", "$i")
                        commentlist.add(Comment(body[i].author_username, body[i].reply))
                        Log.i("댓글 추가됨", "$i" + " " + commentlist[i].Username)
                    }

                    val comment = CommentsAdapter(this@Comments, commentlist)
                    commentslist.adapter = comment
//


                }

                else {

                }
                Log.i("dsdsd", "$raw")
                Log.i("body", " "+ response?.body())
            }
        })

        //ConnectServer(this).getComment(ID)

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

/* Comment("sarang", "사진 잘 봤어요!"),
            Comment("big_guy", "좋아요!")*/

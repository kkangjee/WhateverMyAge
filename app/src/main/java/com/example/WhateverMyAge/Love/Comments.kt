package com.example.WhateverMyAge.Love

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.WhateverMyAge.Guide.Settings.toast
import com.example.WhateverMyAge.Main.*
import com.example.WhateverMyAge.MyInformation
import com.example.WhateverMyAge.R
import com.example.WhateverMyAge.signedin
import com.example.WhateverMyAge.user_name
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.activity_comments.bt_back
import kotlinx.android.synthetic.main.activity_comments_adapter.*
import kotlinx.android.synthetic.main.activity_my_information.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

lateinit var _Comment_Activity: Activity

lateinit var commentsRV: RecyclerView;


class Comments : AppCompatActivity() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://frozen-cove-44670.herokuapp.com/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

        .build()

    var server = retrofit.create(Service::class.java)

    var postID: Int = 0

    fun postComment(posting: Int, Reply: String) {
        server.postComment(posting, Reply, user_name, signedin).enqueue(object : Callback<PostsForm> {
            override fun onFailure(call: Call<PostsForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
            }

            override fun onResponse(call: Call<PostsForm>, response: Response<PostsForm>) {
                val raw = response.raw().toString()
                //  val body = response.body()!!
                if (response?.code().toString() == "201") {
                    // test.text = response?.body().toString()
                    //commentlist = arrayListOf
                    reply.setText("")
                    Log.i("posting", "$posting")
                    var commentlist: ArrayList<Comment> = arrayListOf()
                    getComment(commentlist, posting, commentslist)
                } else {

                }
                Log.i("dsdsd", "$raw")
                Log.i("dssdssss", " " + posting + " " + reply + " " + user_name + " " + signedin)
                //    Log.i("body", "$body")
            }
        })
    }

    fun deleteComment(id: Int, posting: Int, rc: RecyclerView) {
        var server = retrofit.create(Service::class.java)

        server.deleteComment(id).enqueue(object : Callback<Body> {
            override fun onFailure(call: Call<Body>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
            }

            override fun onResponse(call: Call<Body>, response: Response<Body>) {
                //code = response?.code()
                if (response.code() == 204) {
                    var commentlist: ArrayList<Comment> = arrayListOf()
                    getComment(commentlist, posting, rc)
                    Log.i("postID", "$posting")
                } else {

                }

                Log.i("댓삭", " " + response.raw().toString())

            }
        })
    }


    fun getComment(commentlist: ArrayList<Comment>, ID: Int, rc: RecyclerView) {
        commentsRV = rc
        //commentlist = arrayListOf()
        server.getComment(ID).enqueue(object : Callback<List<CommentsForm>> {
            override fun onFailure(call: Call<List<CommentsForm>>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                val comment = CommentsAdapter(this@Comments, commentlist, this@Comments)
                commentsRV.adapter = comment
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
                        commentlist.add(
                            Comment(
                                body[i].posting,
                                body[i].id,
                                body[i].author_id,
                                body[i].author_username,
                                body[i].reply
                            )
                        )
                        Log.i("댓글 추가됨", "$i" + " " + commentlist[i].Username)
                    }

                    val comment = CommentsAdapter(this@Comments, commentlist, this@Comments)

                    commentsRV.adapter = comment
//
                }
                else {

                }
                Log.i("dsdsd", "$raw")
                Log.i("body", " " + response?.body())
            }
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        _Comment_Activity = this
        // commentsRV  =  commentslist
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        val ID = intent.getStringArrayExtra("Post")
        //ConnectServer(this).getPost(ID, this)
        postID = ID[0].toInt()
        Log.i("postID", "$postID")
        userpic.setImageResource(getResources().getIdentifier("@drawable/story1", "id", packageName))
        authorname.text = ID[2]
        lovecontents.text = ID[3]
        like.text = ID[4]
        comments.text = ID[5]

        var commentlist: ArrayList<Comment> = arrayListOf()
        val cl = findViewById<RecyclerView>(R.id.commentslist)

        getComment(commentlist, ID[0].toInt(), commentslist)
        Log.i("ID[0]", " " + ID[0])

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
            } else {
                if (reply.text.toString() == "") {
                    toast("댓글을 작성해주세요.")
                } else {
                    postComment(ID[0].toInt(), reply.text.toString())
                }
            }
        }

        if (signedin != ID[1].toInt()) {
            findViewById<ImageButton>(R.id.popupmenu).setVisibility(View.GONE)
        } else {
            popupmenu.setOnClickListener {
                val popup = PopupMenu(this, popupmenu)

                menuInflater.inflate(R.menu.postmenu, popup.menu)
                popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                    override
                    fun onMenuItemClick(item: MenuItem): Boolean {
                        when (item.itemId) {
                            R.id.put -> {
                                val intent = Intent(this@Comments, LoveWriteArticle::class.java)
                                val arr: Array<String> = arrayOf(ID[0], ID[3])
                                intent.putExtra("put", arr)
                                intent.putExtra("QuestionAnswerArticle", 3)
                                startActivity(intent)
                                return true
                            }

                            R.id.delete -> {
                                ConnectServer(this@Comments).delPost(ID[0])
                                finish()
                                val LA = _Love_Activity
                                LA.finish()

                                val intent = Intent(this@Comments, LoveActivity::class.java)
                                startActivity(intent)

                                return true
                            }

                            else -> return false
                        }
                    }

                })
                popup.show()
            }
        }

        authorname.setOnClickListener {

            val intent = Intent(this, MyInformation::class.java)
            val arr: Array<String> = arrayOf(ID[1], ID[2])
            intent.putExtra("user_info", arr)
            this.startActivity(intent)

        }


    }

//    val listener = MenuItem.OnMenuItemClickListener() {
//        fun onMenuItemClick(item: MenuItem) : Boolean {
//            when (item.itemId) {
//                R.id.put -> {
//                    //ConnectServer(this).putPost()
//                }
//
//                R.id.delete -> {
//                    ConnectServer(this).delPost()
//                }
//
//                else -> {
//
//                }
//            }
//        }
//    }

}


/* Comment("sarang", "사진 잘 봤어요!"),
            Comment("big_guy", "좋아요!")*/

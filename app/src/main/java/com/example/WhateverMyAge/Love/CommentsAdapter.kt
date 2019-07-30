package com.example.WhateverMyAge.Love

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.example.WhateverMyAge.Guide.Settings.toast
import com.example.WhateverMyAge.Main.Body
import com.example.WhateverMyAge.Main.ConnectServer
import com.example.WhateverMyAge.Main.Service
import com.example.WhateverMyAge.MyInformation
import com.example.WhateverMyAge.R
import com.example.WhateverMyAge.signedin
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.activity_comments_adapter.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class Comment (val Posting : Int, val ID : Int, val UserID : Int, val Username : String, val Comment : String)

class CommentsAdapter (val context : Context, val titlelist : ArrayList<Comment>, val activity : Comments) :
    RecyclerView.Adapter<CommentsAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_comments_adapter, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return titlelist.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(titlelist[position], context)
    }

    inner class Holder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Username = itemView.findViewById<AppCompatButton>(R.id.username)
        val Comment = itemView.findViewById<AppCompatButton>(R.id.comment)
        fun bind(comments: Comment, context: Context) {
            Username.text = comments.Username
            Comment.text = comments.Comment

            Username.setOnClickListener {
                val intent = Intent(activity, MyInformation::class.java)
                val arr : Array<String> = arrayOf(comments.UserID.toString(), comments.Username)
                intent.putExtra("user_info", arr)
                activity.startActivity(intent)
            }

            Comment.setOnClickListener {
                activity.toast("길게 누르면 삭제합니다.")
            }

            Comment.setOnLongClickListener(object : View.OnLongClickListener {
                override
                fun onLongClick(v : View) : Boolean {


                    if (signedin == comments.UserID)
                        activity.deleteComment(comments.ID, comments.Posting, commentsRV)
////                    if (signedin == comments.UserID)
////                        Comments().deleteComment(comments.ID)
//
//                    val retrofit = Retrofit.Builder()
//                        .baseUrl("https://frozen-cove-44670.herokuapp.com/")
//                        .addConverterFactory(ScalarsConverterFactory.create())
//                        .addConverterFactory(GsonConverterFactory.create())
//
//                        .build()
//
//                    var server = retrofit.create(Service::class.java)
//
//                    server.deleteComment(comments.ID).enqueue(object : Callback<Body> {
//                        override fun onFailure(call: Call<Body>, t: Throwable) {
//                            Log.e("서버와 통신에 실패했습니다.", "Error!")
//                        }
//
//                        override fun onResponse(call: Call<Body>, response: Response<Body>) {
//                            //code = response?.code()
//                            if (response.code() == 204) {
//                                // test.text = response?.body().toString()
//                            } else {
//
//                            }
//
//                            Log.i("댓삭", " " + response.raw().toString())
//
//                        }
//                    })
//

                    return true
                }
            })
        }
    }
}
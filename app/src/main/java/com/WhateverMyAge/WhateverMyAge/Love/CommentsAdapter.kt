package com.WhateverMyAge.WhateverMyAge.Love

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.WhateverMyAge.WhateverMyAge.Guide.Settings.toast
import com.WhateverMyAge.WhateverMyAge.MyInformation
import com.WhateverMyAge.WhateverMyAge.R
import com.WhateverMyAge.WhateverMyAge.signedin

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
                if (signedin == comments.UserID)
                    activity.toast("길게 누르면 삭제합니다.")
            }

            Comment.setOnLongClickListener(object : View.OnLongClickListener {
                override
                fun onLongClick(v : View) : Boolean {
                    if (signedin == comments.UserID)
                        activity.deleteComment(comments.ID, comments.Posting, commentsRV)
                    return true
                }
            })
        }
    }
}
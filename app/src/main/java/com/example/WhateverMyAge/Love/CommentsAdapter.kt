package com.example.WhateverMyAge.Love

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.example.WhateverMyAge.R

class Comment (val Username : String, val Comment : String)

class CommentsAdapter (val context : Context, val titlelist : ArrayList<Comment>) :
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
        val Comment = itemView.findViewById<AppCompatTextView>(R.id.comment)
        fun bind(comments: Comment, context: Context) {
            Username.text = comments.Username
            Comment.text = comments.Comment
        }
    }
}
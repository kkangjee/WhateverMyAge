package com.example.WhateverMyAge

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView

class QuestionsTitles (val Title : String)

class QuestionTitlesAdapter (val context : Context, val titlelist : ArrayList<QuestionsTitles>) :
    RecyclerView.Adapter<QuestionTitlesAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.activity_questions_adapter, parent, false
        )
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return titlelist.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(titlelist[position], context)
    }

    inner class Holder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Title = itemView.findViewById<AppCompatButton>(R.id.questiontitle)

        fun bind(questiontitles: QuestionsTitles, context: Context) {
            Title.text = questiontitles.Title
        }
    }
}
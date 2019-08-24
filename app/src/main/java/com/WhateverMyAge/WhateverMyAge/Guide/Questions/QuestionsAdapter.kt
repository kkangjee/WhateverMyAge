package com.WhateverMyAge.WhateverMyAge.Guide.Questions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.WhateverMyAge.WhateverMyAge.Love.Comments
import com.WhateverMyAge.WhateverMyAge.R

class QuestionsTitles (val Title : String, val Id : Int, val Content : String, val photo : String?, val Author : String, val Author_id : Int, val Cnt : Int)

class QuestionTitlesAdapter (val context : Context, val titlelist : ArrayList<QuestionsTitles>, val activity : Activity) :
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

            Title.setOnClickListener {
                var intent = Intent(activity, Comments::class.java)
                val arr : Array<String> = arrayOf(questiontitles.Id.toString(), questiontitles.Author, questiontitles.Author_id.toString(), questiontitles.Title, questiontitles.Content, (if (questiontitles.photo != null) questiontitles.photo else ""), "Q", questiontitles.Cnt.toString())
                intent.putExtra("Question", arr)
                activity.startActivity(intent)

            }
        }
    }
}
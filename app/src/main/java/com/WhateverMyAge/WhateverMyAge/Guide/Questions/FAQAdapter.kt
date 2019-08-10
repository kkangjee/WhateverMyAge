package com.WhateverMyAge.WhateverMyAge.Guide.Questions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.WhateverMyAge.WhateverMyAge.R

class FAQTitles (val Title : String, val Num : Int)

class FAQTitlesAdapter (val context : Context, val titlelist : ArrayList<FAQTitles>, val activity : Activity) :
    RecyclerView.Adapter<FAQTitlesAdapter.Holder>() {
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

        fun bind(FAQtitles: FAQTitles, context: Context) {
            Title.text = FAQtitles.Title

            Title.setOnClickListener {
                val intent = Intent(activity, FAQContent::class.java)
                val arr : ArrayList<String> = arrayListOf(FAQtitles.Num.toString(), FAQtitles.Title)
                intent.putExtra("FAQ", arr)
                activity.startActivity(intent)
            }

        }
    }
}
package com.example.makeref

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

class LoveArticles (val Userpic : String, val Username:String, val LoveContents : String, val Like : String, val Comments : String)

class LoveArticlesAdapter (val context : Context, val contentlist : ArrayList<LoveArticles>) :
        RecyclerView.Adapter<LoveArticlesAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.lovearticles, parent, false
        )
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return contentlist.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(contentlist[position], context)
    }

    inner class Holder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Userpic = itemView.findViewById<AppCompatImageButton>(R.id.userpic)
        val Username = itemView.findViewById<AppCompatButton>(R.id.username)
        val LoveContents = itemView.findViewById<AppCompatButton>(R.id.lovecontents)
        val Like = itemView.findViewById<AppCompatTextView>(R.id.like)
        val Comments = itemView.findViewById<AppCompatTextView>(R.id.comments)

        fun bind(lovearticles: LoveArticles, context: Context) {
            if (lovearticles.Userpic != "") {
                val resource = context.resources.getIdentifier(lovearticles.Userpic, "drawable", context.packageName)
                Userpic.setImageResource(resource)
            } else {
                Userpic.setImageResource(R.mipmap.ic_launcher)
            }

            Username.text = lovearticles.Username
            LoveContents.text = lovearticles.LoveContents
            Like.text = lovearticles.Like
            Comments.text = lovearticles.Comments
        }
    }
}
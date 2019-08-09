package com.G1B4.WhateverMyAge.Love

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.View
import android.view.ViewTreeObserver
import android.widget.*
import androidx.appcompat.widget.*
import com.G1B4.WhateverMyAge.Main.ImageURL
import com.G1B4.WhateverMyAge.Main.Profile
import com.G1B4.WhateverMyAge.Main.Service
import com.G1B4.WhateverMyAge.MyInformation
import com.G1B4.WhateverMyAge.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class LoveArticles(
    var ID: Int,
    var Userpic: String,
    var UserID: Int,
    var Username: String,
    var Title: String,
    var LoveContents: String,
    var Like: String,
    var Comments: String,
    var Lat: Double,
    var Lng: Double,
    var Picture: String?
)

public class ImageRounding(val image: ImageView) {
    fun rounding() {
        image.setBackground(ShapeDrawable(OvalShape()))
        image.setClipToOutline(true)
    }
}

class OnViewGlobalLayoutListener(private val view: View) : ViewTreeObserver.OnGlobalLayoutListener {

    //사진 줄이기
    override fun onGlobalLayout() {
        if (view.height > maxHeight || view.width > maxWidth) {
            view.layoutParams.height = maxHeight
            view.layoutParams.width = maxWidth
        }
    }

    companion object {
        private val maxHeight = 1000
        private val maxWidth = 1000
    }
}

class LoveArticlesAdapter(val context: Context, val contentlist: ArrayList<LoveArticles>, val activity: Activity) :
    RecyclerView.Adapter<LoveArticlesAdapter.Holder>() {

    var love = context

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

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val Userpic = itemView.findViewById<AppCompatImageButton>(R.id.userpic)
        val Username = itemView.findViewById<AppCompatButton>(R.id.username)
        val LoveContents = itemView.findViewById<FrameLayout>(R.id.lovecontents)
        val Picture = itemView.findViewById<AppCompatImageView>(R.id.uploadedPicture)
        val Love = itemView.findViewById<AppCompatButton>(R.id.love)
        val Comments = itemView.findViewById<AppCompatTextView>(R.id.comments)

        fun bind(lovearticles: LoveArticles, context: Context) {
            Picture.viewTreeObserver.addOnGlobalLayoutListener(OnViewGlobalLayoutListener(Picture))

            if (lovearticles.Picture != null) {
                lovearticles.Picture = "https://frozen-cove-44670.herokuapp.com" + lovearticles.Picture
                var bit = ImageURL().setImageURL(lovearticles.Picture)
                Picture.setImageBitmap(bit)
            } else
                Picture.setVisibility(View.GONE)
            Username.text = lovearticles.Username
            Love.text = lovearticles.LoveContents
            Comments.text = lovearticles.Comments
            LoveContents.setOnClickListener {
                var intent = Intent(activity, com.G1B4.WhateverMyAge.Love.Comments::class.java)
                val arr: Array<String> = arrayOf(
                    lovearticles.ID.toString(),
                    lovearticles.UserID.toString(),
                    lovearticles.Username,
                    lovearticles.LoveContents,
                    lovearticles.Like,
                    lovearticles.Comments,
                    (if (lovearticles.Picture != null) lovearticles.Picture else "")!!,
                    "P"
                )
                intent.putExtra("Post", arr)
                activity.startActivity(intent)
            }

            Love.setOnClickListener {
                var intent = Intent(activity, com.G1B4.WhateverMyAge.Love.Comments::class.java)
                val arr: Array<String> = arrayOf(
                    lovearticles.ID.toString(),
                    lovearticles.UserID.toString(),
                    lovearticles.Username,
                    lovearticles.LoveContents,
                    lovearticles.Like,
                    lovearticles.Comments,
                    (if (lovearticles.Picture != null) lovearticles.Picture else "")!!,
                    "P"
                )
                intent.putExtra("Post", arr)
                activity.startActivity(intent)
            }

            Username.setOnClickListener {
                val intent = Intent(activity, MyInformation::class.java)
                val arr: Array<String> = arrayOf(lovearticles.UserID.toString(), lovearticles.Username)
                intent.putExtra("user_info", arr)
                activity.startActivity(intent)
            }

            val retrofit = Retrofit.Builder()
                .baseUrl("https://frozen-cove-44670.herokuapp.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

                .build()

            var server = retrofit.create(Service::class.java)

            server.getProfilePic(lovearticles.UserID).enqueue(object : Callback<Profile> {
                override fun onFailure(call: Call<Profile>, t: Throwable) {
                    Log.e("서버와 통신에 실패했습니다.", "Error!")
                }

                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                    if (response.code() == 200) {
                        val pic = if (response.body() != null) response.body()!!.user_photo else null
                        Log.i("dsd", "$pic")
                        if (pic != null) {
                            val bit = ImageURL().setImageURL(pic)
                            Userpic.setImageBitmap(bit)
                            Log.i("image bitmap", "$pic")
                            ImageRounding(Userpic).rounding()
                        } else {
                            Log.i("no pic", " dd")
                            val resource =
                                context.resources.getIdentifier(lovearticles.Userpic, "drawable", context.packageName)
                            Userpic.setImageResource(resource)
                            ImageRounding(Userpic).rounding()

                        }
                    } else {

                    }
                    Log.i("profile image", " " + response.raw())
                    Log.i("profile image", " " + response.body())
                }
            })
        }
    }
}
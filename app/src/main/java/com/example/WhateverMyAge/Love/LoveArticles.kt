package com.example.WhateverMyAge.Love

import android.app.ActionBar
import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.location.GnssMeasurement
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.View
import android.view.ViewTreeObserver
import android.widget.*
import androidx.appcompat.widget.*
import androidx.core.content.ContextCompat.startActivity
import com.example.WhateverMyAge.Main.ConnectServer
import com.example.WhateverMyAge.Main.Profile
import com.example.WhateverMyAge.Main.Service
import com.example.WhateverMyAge.MyInformation
import com.example.WhateverMyAge.R
import com.example.WhateverMyAge.TravelAndFood.TravelAPI

import com.example.WhateverMyAge.signedin
import com.example.WhateverMyAge.user_name
import kotlinx.android.synthetic.main.activity_my_information.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.URL

class LoveArticles (var ID : Int, var Userpic : String, var UserID : Int, var Username:String, var Title : String, var LoveContents : String, var Like : String, var Comments : String, var Lat : Double, var Lng : Double, var Picture : String?)


class OnViewGlobalLayoutListener(private val view: View) : ViewTreeObserver.OnGlobalLayoutListener {

    override fun onGlobalLayout() {
        if (view.height > maxHeight || view.width > maxWidth) {
            view.layoutParams.height = maxHeight
            view.layoutParams.width = maxWidth
        }
    }

    companion object {
        private val maxHeight = 3000
        private val maxWidth = 3000
    }
}

class LoveArticlesAdapter (val context : Context, val contentlist : ArrayList<LoveArticles>, val activity: Activity) :
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

    inner class Holder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val Userpic = itemView.findViewById<AppCompatImageButton>(R.id.userpic)
        val Username = itemView.findViewById<AppCompatButton>(R.id.username)
        val LoveContents = itemView.findViewById<FrameLayout>(R.id.lovecontents)
        val LikeButton = itemView.findViewById<AppCompatCheckBox>(R.id.likebutton)
        val Picture = itemView.findViewById<AppCompatImageView>(R.id.uploadedPicture)

        val Love = itemView.findViewById<AppCompatButton>(R.id.love)
        val Like = itemView.findViewById<AppCompatTextView>(R.id.like)
        val Comments = itemView.findViewById<AppCompatTextView>(R.id.comments)

        fun bind(lovearticles: LoveArticles, context: Context) {
//            if (lovearticles.Userpic != "") {
//                val resource = context.resources.getIdentifier(lovearticles.Userpic, "drawable", context.packageName)
//                Userpic.setImageResource(resource)
//            } else {
//                Userpic.setImageResource(R.mipmap.ic_launcher)
//            }

            Picture.viewTreeObserver.addOnGlobalLayoutListener(OnViewGlobalLayoutListener(Picture))

            if (lovearticles.Picture != null) {
                Log.i("이미지 시작!!!!", " " + lovearticles.Picture)
//                var mButton = ImageView(context)
//                //var mView = ViewGroup(context)
//                var pm = LinearLayout.LayoutParams(100, 100)
//                mButton.setLayoutParams(pm)

                var bit = TravelAPI().setImageURL(lovearticles.Picture)
                Picture.setImageBitmap(bit)
                //this.setContentView(mButton)
            }

            else
                Picture.setVisibility(View.GONE)
            Username.text = lovearticles.Username
            Love.text = lovearticles.LoveContents
            Like.text = lovearticles.Like
            Comments.text = lovearticles.Comments

            LoveContents.setOnClickListener {
                //var intent = Intent(LoveActivity@, WholeArticleActivity::class.java)
                //intent.putExtra("id", lovearticles.ID)
                //start
                Log.i("글 아이디는~", " " + lovearticles.ID)
                var intent = Intent(activity, com.example.WhateverMyAge.Love.Comments::class.java)
                val arr : Array<String> = arrayOf (lovearticles.ID.toString(), lovearticles.UserID.toString(), lovearticles.Username, lovearticles.LoveContents, lovearticles.Like, lovearticles.Comments, (if (lovearticles.Picture != null) lovearticles.Picture else "")!!)
                intent.putExtra("Post", arr)
                activity.startActivity(intent)
            }

            Love.setOnClickListener {
                Log.i("글 아이디는~", " " + lovearticles.ID)
                var intent = Intent(activity, com.example.WhateverMyAge.Love.Comments::class.java)
                val arr : Array<String> = arrayOf (lovearticles.ID.toString(), lovearticles.UserID.toString(), lovearticles.Username, lovearticles.LoveContents, lovearticles.Like, lovearticles.Comments, (if (lovearticles.Picture != null) lovearticles.Picture else "")!!, "P")
                intent.putExtra("Post", arr)
                activity.startActivity(intent)
            }

            Username.setOnClickListener {
                Log.i("유저 아이디는~", " " + lovearticles.UserID)
                val intent = Intent(activity, MyInformation::class.java)
                val arr : Array<String> = arrayOf(lovearticles.UserID.toString(), lovearticles.Username)
                intent.putExtra("user_info", arr)
                activity.startActivity(intent)
            }

            LikeButton.setOnClickListener {
                Log.i("글 아이디는~", " " + lovearticles.ID)
                ConnectServer(activity).getLikedUsers(lovearticles.ID)
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
                    //code = response?.code()
                    if (response.code() == 204) {
                        // test.text = response?.body().toString()
                    } else {

                    }
                    Log.i("profile image", " " + response.raw())
                    Log.i("profile image", " " + response.body())

                    val pic = if (response.body() != null) response.body()!!.user_photo else null
                    Log.i("dsd", "$pic")
                    if (pic != null) {
//                        var mButton = ImageView(context)
//                        var pm = LinearLayout.LayoutParams(100, 100)
//
//
//
//                        Log.i("pic", "exists" + pic)
                        val bit = TravelAPI().setImageURL(pic)
                        Userpic.setImageBitmap(bit)
                        Log.i("image bitmap", "$pic")
                    }

                    else {
                        Log.i("no pic", " dd")
                        val resource = context.resources.getIdentifier(lovearticles.Userpic, "drawable", context.packageName)
                        Userpic.setImageResource(resource)

                    }

                }
            })

        }
    }
}
package com.example.WhateverMyAge.Love

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import com.example.WhateverMyAge.Guide.Settings.toast
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_love.*
import android.location.Geocoder
import java.util.*
import android.location.LocationManager
import android.location.Criteria
import android.location.Location
import android.text.TextUtils
import android.provider.Settings.SettingNotFoundException
import android.os.Build
import android.util.Log
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.WhateverMyAge.LoginActivity
import com.example.WhateverMyAge.Main.ConnectServer
import com.example.WhateverMyAge.Main.PermissionCheck
import com.example.WhateverMyAge.Main.PostsForm
import com.example.WhateverMyAge.Main.Service
import com.example.WhateverMyAge.R
import com.example.WhateverMyAge.progressDialog
import com.example.WhateverMyAge.signedin
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.activity_comments.view.*
import kotlinx.android.synthetic.main.activity_comments.view.popupmenu
import kotlinx.android.synthetic.main.activity_love.bt_back
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import kotlin.collections.ArrayList

var lat: Double = 0.0
var lng: Double = 0.0
var _Love_Activity: Activity = LoveActivity()
var WholeOrAround = 0

fun isLocationEnabled(context: Context): Boolean {
    val locationMode: Int

    try {
        locationMode = android.provider.Settings.Secure.getInt(
            context.contentResolver,
            android.provider.Settings.Secure.LOCATION_MODE
        )

    } catch (e: SettingNotFoundException) {
        e.printStackTrace()
        return false
    }

    return locationMode != android.provider.Settings.Secure.LOCATION_MODE_OFF
}


class LoveActivity : AppCompatActivity() {
    fun showPost(contentlist: ArrayList<LoveArticles>) {
        server.showPost().enqueue(object : Callback<List<PostsForm>> {
            override fun onFailure(call: Call<List<PostsForm>>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
            }

            override fun onResponse(call: Call<List<PostsForm>>, response: Response<List<PostsForm>>) {
                val count = response.body()!!.lastIndex
                //contentlist = arrayListOf()
                val body = response.body()!!
                Log.i("dsdsd", "$count")
                Log.i("body", "$body")

                if (response.code().toString() == "200") {
                    if (WholeOrAround == 1) {
                        for (i in 0..count) {
                            if (body[i].photo != null)
                                Log.i("photo", " " + body[i].photo)

                            if (distance(
                                    body[i].lat,
                                    body[i].lng,
                                    lat,
                                    lng,
                                    "kilometer"
                                ) < 5 && lat != 0.0 && lng != 0.0
                            ) {
                                contentlist.add(
                                    LoveArticles(
                                        body[i].id,
                                        "story1",
                                        body[i].author_id,
                                        body[i].author_username,
                                        body[i].title,
                                        body[i].content,
                                        body[i].like.toString(),
                                        body[i].cnt.toString(),
                                        body[i].lat,
                                        body[i].lng,
                                        body[i].photo
                                    )
                                )
                            }
                        }
                    } else {
                        for (i in 0..count) {
                            contentlist.add(
                                LoveArticles(
                                    body[i].id,
                                    "story1",
                                    body[i].author_id,
                                    body[i].author_username,
                                    body[i].title,
                                    body[i].content,
                                    body[i].like.toString(),
                                    body[i].cnt.toString(),
                                    body[i].lat,
                                    body[i].lng,
                                    body[i].photo
                                )
                            )
                        }
                    }

                }

                val love = LoveArticlesAdapter(this@LoveActivity, contentlist, this@LoveActivity)
                lovearticles.adapter = love
                loadingEnd()
            }
        })
    }


    fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double, unit: String): Double {

        val theta = lon1 - lon2
        var dist =
            Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(
                deg2rad(theta)
            )

        dist = Math.acos(dist)
        dist = rad2deg(dist)
        dist = dist * 60 * 1.1515

        if (unit == "kilometer") {
            dist = dist * 1.609344
        } else if (unit == "meter") {
            dist = dist * 1609.344
        }

        return (dist)
    }

    fun deg2rad(deg: Double): Double {
        return (deg * Math.PI / 180.0)
    }

    // This function converts radians to decimal degrees
    fun rad2deg(rad: Double): Double {
        return (rad * 180 / Math.PI)
    }

//    var contentlist = arrayListOf(
//        LoveArticles("story1", "sarangbang", "오늘은 여기에 놀러 갔어요", "3", "5"),
//        LoveArticles("story2", "whats wrong", "멋쟁이 사자처럼 화이팅", "32", "6"),
//        LoveArticles("story3", "with my age", "내 나이가 어때서", "9", "5"),
//        LoveArticles("story4", "lets do this", "ㅎㅎㅎ", "7", "15")
//    )
    //////////////

    val retrofit = Retrofit.Builder()
        .baseUrl("https://frozen-cove-44670.herokuapp.com/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

        .build()

    var server = retrofit.create(Service::class.java)

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        //로딩 시작
        loading()
        _Love_Activity = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_love)

        // val mSwipe = findViewById<SwipeRefreshLayout>(R.id.swipe)

        bt_locationselect.text = if (WholeOrAround == 1) "내 주변" else "전체 보기"

        //게시판 글 출력
        var contentlist: ArrayList<LoveArticles> = arrayListOf()
        showPost(contentlist)
        //로딩 끝

//        mSwipe.setOnRefreshListener (object : SwipeRefreshLayout.OnRefreshListener {
//            override
//            fun onRefresh() {
//                showPost(contentlist)
//                mSwipe.setRefreshing(false)
//            }
//        })

        ///////////
        val permissioncheck = PermissionCheck(this, this)

        val locationMgr = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()
        criteria.accuracy = Criteria.ACCURACY_COARSE
        criteria.powerRequirement = Criteria.POWER_LOW
        criteria.isAltitudeRequired = false
        criteria.isBearingRequired = false
        criteria.isSpeedRequired = false
        criteria.isCostAllowed = true

//        val love = LoveArticlesAdapter(this, contentlist)
//        lovearticles.adapter = love

        val lm = LinearLayoutManager(this)
        lovearticles.layoutManager = lm
        //lovearticles.setHasFixedSize(true)

        bt_writeArticle.setOnClickListener {
            //setting 화면
            if (signedin == 0) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                val intent = Intent(this, LoveWriteArticle::class.java)
                intent.putExtra("QuestionAnswerArticle", 2)  //게시글 쓰기
                startActivity(intent)
            }
        }

        bt_back.setOnClickListener {
            finish()
        }

        if (permissioncheck.LocationCheck() == 0) {
            if (!isLocationEnabled(this)) {
                toast("위치 사용을 켜면 내 주변의 글을 확인할 수 있어요.")
            } else {
                try {
                    val bestProvider: String? = locationMgr.getBestProvider(criteria, true)

                    var gps = locationMgr.getLastKnownLocation(bestProvider)!!

                    lat = gps.getLatitude()
                    lng = gps.getLongitude()
                }catch(e : SecurityException){

                }
            }
        }


        bt_locationselect.setOnClickListener {


            //주소 출력
//                    val gcd = Geocoder(this, Locale.getDefault())
//                    val addresses = gcd.getFromLocation(lat, lng, 1)
//
//                    val cityName = addresses[0].getAddressLine(0)
//                    val stateName = addresses[0].getAddressLine(1)
//                    val countryName = addresses[0].getAddressLine(2)

            //var dist = distance(lat, lng, 37.3044, 127.0040, "kilometer")

            //toast("$dist" + "km입니다.")
            if (!isLocationEnabled(this)) {
                toast("위치 사용을 켜주세요.")
                val intent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivityForResult(intent, 0)
            } else {

                val popup = PopupMenu(this, bt_locationselect)

                menuInflater.inflate(R.menu.showpost, popup.menu)
                popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                    override
                    fun onMenuItemClick(item: MenuItem): Boolean {
                        when (item.itemId) {
                            R.id.currentLocation -> {
                                WholeOrAround = 1
                                val LA = _Love_Activity
                                LA.finish()

                                val intent = Intent(this@LoveActivity, LoveActivity::class.java)
                                startActivity(intent)

                                return true
                            }

                            R.id.whole -> {
                                WholeOrAround = 0
                                val LA = _Love_Activity
                                LA.finish()

                                val intent = Intent(this@LoveActivity, LoveActivity::class.java)
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

    }

    fun loading() {
        //로딩
        android.os.Handler().postDelayed(
            {
                progressDialog = ProgressDialog(this@LoveActivity)
                progressDialog!!.setIndeterminate(true)
                progressDialog!!.setMessage("잠시만 기다려 주세요")
                progressDialog!!.show()
            }, 0
        )
    }


}
fun loadingEnd() {
    android.os.Handler().postDelayed(
        {
            progressDialog?.dismiss()

        }, 0
    )
}


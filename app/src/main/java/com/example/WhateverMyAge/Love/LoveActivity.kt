package com.example.WhateverMyAge.Love

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
import com.example.WhateverMyAge.LoginActivity
import com.example.WhateverMyAge.Main.ConnectServer
import com.example.WhateverMyAge.Main.PermissionCheck
import com.example.WhateverMyAge.R
import com.example.WhateverMyAge.signedin

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
    var contentlist =  ConnectServer(this).postList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_love)
        val permissioncheck = PermissionCheck(this, this)

        val locationMgr = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()
        criteria.accuracy = Criteria.ACCURACY_COARSE
        criteria.powerRequirement = Criteria.POWER_LOW
        criteria.isAltitudeRequired = false
        criteria.isBearingRequired = false
        criteria.isSpeedRequired = false
        criteria.isCostAllowed = true

        val love = LoveArticlesAdapter(this, contentlist)
        lovearticles.adapter = love

        val lm = LinearLayoutManager(this)
        lovearticles.layoutManager = lm
        //lovearticles.setHasFixedSize(true)

        bt_writeArticle.setOnClickListener {
            //setting 화면
            if (signedin == 0) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, LoveWriteArticle::class.java)
                intent.putExtra("QuestionAnswerArticle", 2)  //게시글 쓰기
                startActivity(intent)
            }
        }

        bt_back.setOnClickListener {
            finish()

        }

        bt_locationselect.setOnClickListener {
            if (permissioncheck.LocationCheck() == 0) {
                if (!isLocationEnabled(this)) {
                    toast("위치 사용을 켜주세요.")
                    val intent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivityForResult(intent, 0)
                } else {
                    val bestProvider = locationMgr.getBestProvider(criteria, true)

                    var gps = locationMgr.getLastKnownLocation(bestProvider)!!

                    val lat = gps.getLatitude()
                    val lng = gps.getLongitude()

                    //주소 출력
//                    val gcd = Geocoder(this, Locale.getDefault())
//                    val addresses = gcd.getFromLocation(lat, lng, 1)
//
//                    val cityName = addresses[0].getAddressLine(0)
//                    val stateName = addresses[0].getAddressLine(1)
//                    val countryName = addresses[0].getAddressLine(2)

                    var dist = distance(lat, lng, 37.3044, 127.0040, "kilometer")

                    toast("$dist" + "km입니다.")

                }
            }
        }
    }
}
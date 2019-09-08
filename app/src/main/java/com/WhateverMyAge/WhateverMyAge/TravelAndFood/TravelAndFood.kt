package com.WhateverMyAge.WhateverMyAge.TravelAndFood

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import com.WhateverMyAge.WhateverMyAge.Guide.Settings.toast
import com.WhateverMyAge.WhateverMyAge.Main.PermissionCheck
import com.WhateverMyAge.WhateverMyAge.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_travel_and_food.*
import kotlinx.android.synthetic.main.activity_travel_and_food.bt_back
import android.hardware.SensorManager.getAltitude
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.location.LocationListener

var lat : Double = 0.0
var lng : Double = 0.0

class TravelAndFood : AppCompatActivity() {
    private val adapter = TravelAndFoodAdapter(supportFragmentManager)
    private val mLocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            //여기서 위치값이 갱신되면 이벤트가 발생한다.
            //값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.

            Log.d("test", "onLocationChanged, location:$location")
            lng = location.longitude //경도
            lat = location.latitude   //위도
            val altitude = location.altitude   //고도
            val accuracy = location.accuracy    //정확도
            val provider = location.provider   //위치제공자
            //Gps 위치제공자에 의한 위치변화. 오차범위가 좁다.
            //Network 위치제공자에 의한 위치변화
            //Network 위치는 Gps에 비해 정확도가 많이 떨어진다.
        }

        override fun onProviderDisabled(provider: String) {
            // Disabled시
            Log.d("test", "onProviderDisabled, provider:$provider")
        }

        override fun onProviderEnabled(provider: String) {
            // Enabled시
            Log.d("test", "onProviderEnabled, provider:$provider")
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
            // 변경시
            Log.d("test", "onStatusChanged, provider:$provider, status:$status ,Bundle:$extras")
        }
    }

    fun isLocationEnabled(context: Context): Boolean {
        val locationMode: Int

        try {
            locationMode = android.provider.Settings.Secure.getInt(
                context.contentResolver,
                android.provider.Settings.Secure.LOCATION_MODE
            )

        } catch (e: Settings.SettingNotFoundException) {
            e.printStackTrace()
            return false
        }

        return locationMode != android.provider.Settings.Secure.LOCATION_MODE_OFF
    }


    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        val which = intent.getIntExtra("which", -1)


        var arguments = Bundle()
        arguments.putInt("Which", which)
        val myfragment = Fragment()
        myfragment.setArguments(arguments)
        supportFragmentManager.beginTransaction().replace(R.id.travelandfood, myfragment)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_and_food)
        travelandfood.adapter = TravelAndFood@ adapter

        val intent = Intent()
        intent.putExtra("WhichExplanation", 1)

        travelorfood.setupWithViewPager(travelandfood)

        val f : Float = 1.toFloat()

        if (PermissionCheck(this, this).LocationCheck() == 0) {
            val locationMgr = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationMgr.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, // 등록할 위치제공자
                100, // 통지사이의 최소 시간간격 (miliSecond)
                f, // 통지사이의 최소 변경거리 (m)
                mLocationListener
            )
            locationMgr.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                100, // 통지사이의 최소 시간간격 (miliSecond)
                f, // 통지사이의 최소 변경거리 (m)
                mLocationListener
            )

//            val criteria = Criteria()
//            criteria.accuracy = Criteria.ACCURACY_COARSE
//            criteria.powerRequirement = Criteria.POWER_LOW
//            criteria.isAltitudeRequired = false
//            criteria.isBearingRequired = false
//            criteria.isSpeedRequired = false
//            criteria.isCostAllowed = true

//            if (!isLocationEnabled(this)) {
//                toast("위치 사용을 켜면 내 주변의 글을 확인할 수 있어요.")
//            } else {
//
//                var gps: Location? = null
//                val bestProvider: String? = locationMgr.getBestProvider(criteria, true)
//                Log.i("provider", "$bestProvider")
//
//
//                try {
//                    gps = locationMgr.getLastKnownLocation(bestProvider!!)!!
//                } catch (e: SecurityException) {
//                    Log.i("위치정보", "not granted")
//                }
//                Log.i("ds", "$lat")
//                Log.i("ds", "$lng")
//
//                lat = if (gps != null) gps.getLatitude() else 0.0
//                lng = if (gps != null) gps.getLongitude() else 0.0
//            }
        }

        bt_back.setOnClickListener {
            finish()
        }

//        MobileAds.initialize(this, getString(R.string.admob_app_id))
//        val adRequest = AdRequest.Builder().build()
//        adView.loadAd(adRequest)
    }
}

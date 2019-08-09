package com.G1B4.WhateverMyAge.TravelAndFood

import android.content.Context
import android.content.Intent
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.G1B4.WhateverMyAge.Guide.Settings.toast
import com.G1B4.WhateverMyAge.Main.Loading
import com.G1B4.WhateverMyAge.Love.isLocationEnabled
import com.G1B4.WhateverMyAge.Love.lat
import com.G1B4.WhateverMyAge.Love.lng
import com.G1B4.WhateverMyAge.Main.PermissionCheck
import com.G1B4.WhateverMyAge.R
import kotlinx.android.synthetic.main.activity_travel_and_food.*
import kotlinx.android.synthetic.main.activity_travel_and_food.bt_back

class TravelAndFood : AppCompatActivity() {
    private val adapter = TravelAndFoodAdapter(supportFragmentManager)

    //@SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        val which = intent.getIntExtra("which", -1)

        var arguments = Bundle()
        arguments.putInt("Which", which)
        val myfragment = Fragment()
        myfragment.setArguments(arguments)
        supportFragmentManager.beginTransaction().replace(R.id.travelandfood, myfragment)

        super.onCreate(savedInstanceState)
        val Loading = Loading(this)
        Loading.loading()
        setContentView(R.layout.activity_travel_and_food)
        travelandfood.adapter = TravelAndFood@ adapter

        val intent = Intent()
        intent.putExtra("WhichExplanation", 1)

        travelorfood.setupWithViewPager(travelandfood)
        Loading.loadingEnd()

        if (PermissionCheck(this, this).LocationCheck() == 0) {
            val locationMgr = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val criteria = Criteria()
            criteria.accuracy = Criteria.ACCURACY_COARSE
            criteria.powerRequirement = Criteria.POWER_LOW
            criteria.isAltitudeRequired = false
            criteria.isBearingRequired = false
            criteria.isSpeedRequired = false
            criteria.isCostAllowed = true

            if (!isLocationEnabled(this)) {
                toast("위치 사용을 켜면 내 주변의 글을 확인할 수 있어요.")
            } else {

                var gps: Location? = null
                val bestProvider: String? = locationMgr.getBestProvider(criteria, true)
                Log.i("provider", "$bestProvider")

                try {
                    gps = locationMgr.getLastKnownLocation(bestProvider!!)!!
                } catch (e: SecurityException) {
                    Log.i("위치정보", "not granted")
                }
                Log.i("ds", "$lat")
                Log.i("ds", "$lng")

                lat = if (gps != null) gps.getLatitude() else 0.0
                lng = if (gps != null) gps.getLongitude() else 0.0
            }
        }

        bt_back.setOnClickListener {
            finish()
        }
    }

}

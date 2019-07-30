package com.example.WhateverMyAge.TravelAndFood

import android.content.Context
import android.content.Intent
import android.location.Criteria
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.WhateverMyAge.Guide.Settings.toast
import com.example.WhateverMyAge.Love.isLocationEnabled
import com.example.WhateverMyAge.Love.lat
import com.example.WhateverMyAge.Love.lng
import com.example.WhateverMyAge.Main.LoadingActivity
import com.example.WhateverMyAge.Main.PermissionCheck
import com.example.WhateverMyAge.R
import kotlinx.android.synthetic.main.activity_travel_and_food.*
import kotlinx.android.synthetic.main.activity_travel_and_food.bt_back

class TravelAndFood : AppCompatActivity() {
    private val adapter = TravelAndFoodAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        val which = intent.getIntExtra("which", -1)

        var arguments = Bundle()
        arguments.putInt("Which", which)
        val myfragment = Fragment()
        myfragment.setArguments(arguments)
        supportFragmentManager.beginTransaction().replace(R.id.travelandfood, myfragment)

        //로딩 액티비티 실행
        val intent_loading = Intent(this, LoadingActivity::class.java)
        startActivity(intent_loading)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_and_food)
        travelandfood.adapter = TravelAndFood@adapter
        val intent = Intent()
        intent.putExtra("WhichExplanation", 1)

        travelorfood.setupWithViewPager(travelandfood)

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
                val bestProvider: String? = locationMgr.getBestProvider(criteria, true)

                var gps = locationMgr.getLastKnownLocation(bestProvider)!!

                lat = gps.getLatitude()
                lng = gps.getLongitude()
            }
        }


        bt_back.setOnClickListener{
            finish()
        }




    }
}

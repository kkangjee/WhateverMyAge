package com.WhateverMyAge.WhateverMyAge.Practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.WhateverMyAge.WhateverMyAge.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_practice.*

class PracticeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice)

        bt_pages.setOnClickListener {
            val intent = Intent(this, PageSlide::class.java)
            startActivity(intent)
        }

        bt_scroll.setOnClickListener {
            val intent = Intent(this, Scroll::class.java)
            startActivity(intent)
        }

        bt_zoom.setOnClickListener {
            val intent = Intent(this, Zoom::class.java)
            startActivity(intent)
        }
        bt_longclick.setOnClickListener {
            val intent = Intent(this, longclick::class.java)
            startActivity(intent)
        }
        bt_back.setOnClickListener{
            finish()
        }
        MobileAds.initialize(this, getString(R.string.admob_app_id))
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }
}

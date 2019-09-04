package com.WhateverMyAge.WhateverMyAge.Practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.WhateverMyAge.WhateverMyAge.R
import kotlinx.android.synthetic.main.activity_practice.bt_back

class Zoom : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoom)

        bt_back.setOnClickListener{
            finish()
        }
    }
}

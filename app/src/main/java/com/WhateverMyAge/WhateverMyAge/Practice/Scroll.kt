package com.WhateverMyAge.WhateverMyAge.Practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.WhateverMyAge.WhateverMyAge.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import kotlinx.android.synthetic.main.activity_scroll.*

class Scroll : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)

        bt_back.setOnClickListener {
            finish()
        }

        val res = resources
        val gifImage = GlideDrawableImageViewTarget(scroll)
        val slide = res.getIdentifier("howtoscroll", "drawable", this.packageName)

        Glide.with(this).load(slide).into(gifImage)
    }
}
